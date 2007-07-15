package net.sf.anathema.character.points;

import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.experience.IExperience;

import org.eclipse.ui.IEditorInput;

public class PointViewInputFactory {

  private static final NullPointViewInput nullInput = new NullPointViewInput();
  private final ModelExtensionPoint modelExtensionPoint = new ModelExtensionPoint();
  private final CharacterTemplateProvider templateProvider = new CharacterTemplateProvider();
  private IPointViewInput lastInput;
  private boolean lastExperienced;

  public IPointViewInput createEditorInput(IEditorInputProvider inputProvider) {
    if (inputProvider == null) {
      return rememberInput(nullInput);
    }
    IEditorInput editorInput = inputProvider.getEditorInput();
    IModelIdentifier modelIdentifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      return rememberInput(nullInput);
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
    this.lastExperienced = experience.isExperienced();
    return rememberInput(new PointViewInput(characterId, modelExtensionPoint.getExperiencePointConfigurations(
        templateProvider,
        characterId)));
  }

  private IPointViewInput rememberInput(IPointViewInput input) {
    this.lastInput = input;
    return input;
  }
}