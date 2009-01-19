package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.editors.CharacterIdProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;

import org.eclipse.ui.IEditorInput;

public class PointViewInputStore implements IValueListInputStore {

  private static final NullCharacterValueEntryFactory nullInput = new NullCharacterValueEntryFactory();
  private ICharacterValueEntryFactory lastInput;
  private boolean lastExperienced;
  private final PointValueEntryFactoryFactory factory;

  public PointViewInputStore() {
    this(new PointConfigurationExtensionPoint());
  }

  public PointViewInputStore(IPointConfigurationProvider provider) {
    this.factory = new PointValueEntryFactoryFactory(provider, new CharacterTemplateProvider());
  }

  public ICharacterValueEntryFactory getEntriesFactory(IEditorInput editorInput) {
    ICharacterId characterId = new CharacterIdProvider().getCharacterId(editorInput);
    if (characterId == null) {
      return storeInput(nullInput);
    }
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