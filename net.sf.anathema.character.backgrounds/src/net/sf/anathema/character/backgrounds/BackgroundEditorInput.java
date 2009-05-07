package net.sf.anathema.character.backgrounds;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.backgrounds.model.IBackgroundAdditionListener;
import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.display.IntViewImageProvider;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.groupeditor.NullFavorizationInteraction;
import net.sf.anathema.character.trait.groupeditor.dynamic.IIntViewImageProvider;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.interactive.InteractiveTraitFactory;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.preference.TraitPreferenceFactory;
import net.sf.anathema.lib.control.GenericControl;

import org.eclipse.core.resources.IFile;

public class BackgroundEditorInput extends AbstractCharacterModelEditorInput<IBackgroundModel> {

  private final IBackgroundModel model;
  private final ITraitCollectionContext context;
  private final GenericControl<IBackgroundAdditionListener<IInteractiveTrait>> control = new GenericControl<IBackgroundAdditionListener<IInteractiveTrait>>();
  private final ICharacterId characterId;

  public BackgroundEditorInput(
      IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      ICharacterId characterId,
      IBackgroundModel model,
      ITraitCollectionContext context) {
    super(file, imageUrl, displayNameProvider, new BackgroundPersister());
    this.characterId = characterId;
    this.model = model;
    this.context = context;
    startListeningForNewBackgrounds();
  }

  private void startListeningForNewBackgrounds() {
    model.addModificationListener(new IBackgroundAdditionListener<IBasicTrait>() {
      @Override
      public void traitAdded(final IBasicTrait trait) {
        control.forAllDo(new IClosure<IBackgroundAdditionListener<IInteractiveTrait>>() {
          @Override
          public void execute(IBackgroundAdditionListener<IInteractiveTrait> listener) throws RuntimeException {
            InteractiveTraitFactory factory = createTraitFactory();
            IInteractiveTrait interactiveTrait = convertTrait(factory, trait);
            listener.traitAdded(interactiveTrait);
          }
        });
      }
    });
  }

  @Override
  protected String getModelId() {
    return IBackgroundModel.MODEL_ID;
  }

  @Override
  public IBackgroundModel getItem() {
    return model;
  }

  public Iterable<IInteractiveTrait> getBackgrounds() {
    List<IInteractiveTrait> traits = new ArrayList<IInteractiveTrait>();
    InteractiveTraitFactory traitFactory = createTraitFactory();
    for (IBasicTrait trait : model.getAllTraits()) {
      traits.add(convertTrait(traitFactory, trait));
    }
    return traits;
  }

  private InteractiveTraitFactory createTraitFactory() {
    IExperience experience = context.getExperience();
    ITraitPreferences preferences = TraitPreferenceFactory.create();
    IEditorInputConfiguration configuration = new BackgroundConfiguration();
    IFavorizationInteraction favorization = new NullFavorizationInteraction();
    return new InteractiveTraitFactory(preferences, experience, configuration, favorization);
  }

  private IInteractiveTrait convertTrait(InteractiveTraitFactory traitFactory, IBasicTrait trait) {
    return traitFactory.create(trait, context.getValidators(trait.getTraitType().getId()));
  }

  public void addModificationListener(IBackgroundAdditionListener<IInteractiveTrait> backgroundAdditionListener) {
    control.addListener(backgroundAdditionListener);
  }

  public void removeModificationListener(IBackgroundAdditionListener<IInteractiveTrait> additionListener) {
    control.removeListener(additionListener);
  }

  public IIntViewImageProvider getImageProvider() {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    String activeImageId = characterType.getTraitImageId();
    return new IntViewImageProvider(activeImageId);
  }

  public ICharacterId getCharacterId() {
    return characterId;
  }
}