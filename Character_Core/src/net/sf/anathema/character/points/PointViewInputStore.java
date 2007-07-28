package net.sf.anathema.character.points;

import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.model.internal.IPointConfigurationProvider;
import net.sf.anathema.character.experience.IExperience;

import org.eclipse.ui.IEditorInput;

public class PointViewInputStore {

  private static final NullPointViewInput nullInput = new NullPointViewInput();
  private IPointViewInput lastInput;
  private boolean lastExperienced;
  private final PointViewInputFactory factory;

  public PointViewInputStore(IPointConfigurationProvider provider) {
    this.factory = new PointViewInputFactory(provider);
  }

  public IPointViewInput createEditorInput(IEditorInputProvider inputProvider) {
    if (inputProvider == null) {
      return storeInput(nullInput);
    }
    IEditorInput editorInput = inputProvider.getEditorInput();
    IModelIdentifier modelIdentifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      return storeInput(nullInput);
    }
    ICharacterId characterId = modelIdentifier.getCharacterId();
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(characterId, IExperience.MODEL_ID));
    if (lastInput != null
        && characterId.equals(lastInput.getCharacterId())
        && experience != null
        && lastExperienced == experience.isExperienced()) {
      return lastInput;
    }
    if (experience != null) {
      this.lastExperienced = experience.isExperienced();
    }
    return storeInput(factory.create(characterId));
  }

  private IPointViewInput storeInput(IPointViewInput input) {
    this.lastInput = input;
    return input;
  }
}