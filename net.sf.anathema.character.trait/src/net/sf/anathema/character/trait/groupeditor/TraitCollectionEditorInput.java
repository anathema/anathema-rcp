package net.sf.anathema.character.trait.groupeditor;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.display.IntViewImageProvider;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.interactive.InteractiveTraitFactory;
import net.sf.anathema.character.trait.interactive.InteractiveTraitGroupTransformer;
import net.sf.anathema.character.trait.persistence.TraitCollectionPersister;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.preference.TraitPreferenceFactory;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.lib.collection.CollectionUtilities;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public class TraitCollectionEditorInput extends AbstractCharacterModelEditorInput<ITraitCollectionModel> implements
    ITraitGroupEditorInput {

  public static final class TraitIdPredicate implements IPredicate<String> {
    private final IIdentificate traitType;

    public TraitIdPredicate(IIdentificate traitType) {
      this.traitType = traitType;
    }

    public boolean evaluate(String arg0) {
      return traitType.getId().equals(arg0);
    }
  }

  private final ITraitCollectionContext context;
  private final IFavorizationInteraction favorizationHandler;
  private final IEditorInputConfiguration configuration;
  private final ITraitPreferences traitPreferences = TraitPreferenceFactory.create();

  public TraitCollectionEditorInput(
      final IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      final ITraitCollectionContext context,
      final IFavorizationInteraction favorizationHandler,
      final IEditorInputConfiguration configuration) {
    super(file, imageUrl, displayNameProvider, new TraitCollectionPersister());
    this.context = context;
    this.favorizationHandler = favorizationHandler;
    this.configuration = configuration;
  }

  @Override
  public ITraitCollectionModel getItem() {
    return context.getCollection();
  }

  /** Creates attribute display groups and displaytraits. Displaytraits must be disposed of by clients. */
  public List<IDisplayTraitGroup<IInteractiveTrait>> createDisplayGroups() {
    return CollectionUtilities.transform(context.getTraitGroups(), new InteractiveTraitGroupTransformer(
        context,
        favorizationHandler,
        traitPreferences,
        configuration));
  }

  @Override
  public List<IInteractiveTrait> getSubTraits(String parentTraitId) {
    List<IInteractiveTrait> interactiveTraits = new ArrayList<IInteractiveTrait>();
    for (IBasicTrait trait : context.getCollection().getSubTraits(parentTraitId)) {
      IInteractiveTrait interactiveTrait = createInteractiveSubTrait(trait);
      interactiveTraits.add(interactiveTrait);
    }
    return interactiveTraits;
  }

  private IInteractiveTrait createInteractiveSubTrait(IBasicTrait trait) {
    List<IValidator> validators = context.getValidators(trait.getTraitType().getId());
    IExperience experience = context.getExperience();
    InteractiveTraitFactory factory = new InteractiveTraitFactory(traitPreferences, trait, experience, validators);
    return factory.create(configuration, new NullFavorizationInteraction());
  }

  @Override
  public IInteractiveTrait addSubTrait(String traitId, String subtraitId) {
    IBasicTrait trait = new BasicTrait(new Identificate(subtraitId));
    context.getCollection().addSubTrait(traitId, trait);
    return createInteractiveSubTrait(trait);
  }

  @Override
  public IFolder getCharacterFolder() {
    return super.getCharacterFolder();
  }

  @Override
  public String getModelId() {
    return configuration.getModelId();
  }

  @Override
  public IEditorInputConfiguration getConfiguration() {
    return configuration;
  }

  @Override
  public IIntViewImageProvider getImageProvider() {
    return new IntViewImageProvider(context.getActiveImageId());
  }

  public ITraitGroup findTraitGroup(IIdentificate traitType) {
    for (ITraitGroup group : context.getTraitGroups()) {
      if (ArrayUtilities.contains(group.getTraitIds(), new TraitIdPredicate(traitType))) {
        return group;
      }
    }
    Object[] arguments = new Object[] { traitType.getId() };
    throw new IllegalArgumentException(MessageFormat.format(
        Messages.TraitCollectionEditorInput_NotInGroupMessage,
        arguments));
  }

  @Override
  public ICharacterId getCharacterId() {
    return getModelIdentifier().getCharacterId();
  }

  @Override
  public boolean supportsSubTraits() {
    return configuration.supportsSubTraits();
  }
}