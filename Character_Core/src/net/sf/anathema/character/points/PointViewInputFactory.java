package net.sf.anathema.character.points;

import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.ui.IEditorInput;

public class PointViewInputFactory {

  private static final NullPointViewInput nullInput = new NullPointViewInput();
  private final ModelExtensionPoint modelExtensionPoint = new ModelExtensionPoint();
  private final CharacterTemplateProvider templateProvider = new CharacterTemplateProvider();
  private IPointViewInput lastInput;

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
    if (lastInput != null && characterId.equals(lastInput.getCharacterId())) {
      return lastInput;
    }
    return rememberInput(new PointViewInput(characterId, modelExtensionPoint.getExperiencePointConfigurations(
        templateProvider,
        characterId)));
  }

  private IPointViewInput rememberInput(IPointViewInput input) {
    this.lastInput = input;
    return input;
  }
}