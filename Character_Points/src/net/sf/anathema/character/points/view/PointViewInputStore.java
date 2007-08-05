package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.editors.ModelIdentifierProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;
import net.sf.anathema.view.valuelist.IValueListInputStore;

import org.eclipse.ui.IEditorInput;

public class PointViewInputStore implements IValueListInputStore {

  private static final NullPointViewInput nullInput = new NullPointViewInput();
  private ICharacterValueEntryFactory lastInput;
  private boolean lastExperienced;
  private final PointViewInputFactory factory;
  
  public PointViewInputStore() {
    this(new PointConfigurationExtensionPoint());
  }

  public PointViewInputStore(IPointConfigurationProvider provider) {
    this.factory = new PointViewInputFactory(provider, new CharacterTemplateProvider());
  }

  public ICharacterValueEntryFactory getViewInput(IEditorInput editorInput) {
    IModelIdentifier modelIdentifier = new ModelIdentifierProvider().getModelIdentifier(editorInput);
    if (modelIdentifier == null) {
      return storeInput(nullInput);
    }
    ICharacterId characterId = modelIdentifier.getCharacterId();
    IExperience experience = getExperienceModel(characterId);
    if (lastInput != null
        && characterId.equals(lastInput.getCharacterId())
        && experience != null
        && lastExperienced == experience.isExperienced()) {
      return lastInput;
    }
    if (experience != null) {
      this.lastExperienced = experience.isExperienced();
    }
    return storeInput(factory.create(characterId, lastExperienced));
  }

  private IExperience getExperienceModel(ICharacterId characterId) {
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, IExperience.MODEL_ID);
    return (IExperience) ModelCache.getInstance().getModel(modelIdentifier);
  }

  private ICharacterValueEntryFactory storeInput(ICharacterValueEntryFactory input) {
    this.lastInput = input;
    return input;
  }
}