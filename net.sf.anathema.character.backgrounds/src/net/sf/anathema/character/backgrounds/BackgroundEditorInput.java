package net.sf.anathema.character.backgrounds;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.groupeditor.NullFavorizationInteraction;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.interactive.InteractiveTraitFactory;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.preference.TraitPreferenceFactory;
import net.sf.anathema.lib.control.GenericControl;

import org.eclipse.core.resources.IFile;

public class BackgroundEditorInput extends AbstractCharacterModelEditorInput<IBackgroundModel> {

  private final IBackgroundModel model;
  private final ICharacterType characterType;
  private final ITraitCollectionContext context;
  private final GenericControl<IBackgroundAdditionListener<IDisplayTrait>> control = new GenericControl<IBackgroundAdditionListener<IDisplayTrait>>();

  public BackgroundEditorInput(
      IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      ICharacterType characterType,
      IBackgroundModel model,
      ITraitCollectionContext context) {
    super(file, imageUrl, displayNameProvider, new BackgroundPersister());
    this.characterType = characterType;
    this.model = model;
    this.context = context;
    startListeningForNewBackgrounds();
  }

  private void startListeningForNewBackgrounds() {
    model.addModificationListener(new IBackgroundAdditionListener<IBasicTrait>() {
      @Override
      public void traitAdded(final IBasicTrait trait) {
        control.forAllDo(new IClosure<IBackgroundAdditionListener<IDisplayTrait>>() {
          @Override
          public void execute(IBackgroundAdditionListener<IDisplayTrait> listener) throws RuntimeException {
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

  public ICharacterType getCharacterType() {
    return characterType;
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
    ITraitPreferences preferences = TraitPreferenceFactory.create();
    IExperience experience = context.getExperience();
    IEditorInputConfiguration configuration = new BackgroundConfiguration();
    IFavorizationInteraction favorization = new NullFavorizationInteraction();
    return new InteractiveTraitFactory(preferences, experience, configuration, favorization);
  }

  private IInteractiveTrait convertTrait(InteractiveTraitFactory traitFactory, IBasicTrait trait) {
    return traitFactory.create(trait, context.getValidators(trait.getTraitType().getId()));
  }

  public void addModificationListener(IBackgroundAdditionListener<IDisplayTrait> backgroundAdditionListener) {
    control.addListener(backgroundAdditionListener);
  }

  public void removeModificationListener(IBackgroundAdditionListener<IDisplayTrait> additionListener) {
    control.removeListener(additionListener);
  }
}