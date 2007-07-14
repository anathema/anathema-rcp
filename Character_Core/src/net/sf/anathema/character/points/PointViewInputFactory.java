package net.sf.anathema.character.points;

import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.ui.IEditorInput;

public class PointViewInputFactory {

  private static final NullExperiencePointViewInput nullInput = new NullExperiencePointViewInput();
  private final ModelExtensionPoint modelExtensionPoint = new ModelExtensionPoint();
  private final CharacterTemplateProvider templateProvider = new CharacterTemplateProvider();

  public IPointViewInput createEditorInput(IEditorInputProvider inputProvider, IPointViewInput viewInput) {
    if (inputProvider == null) {
      return nullInput;
    }
    IEditorInput editorInput = inputProvider.getEditorInput();
    IModelIdentifier modelIdentifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      return nullInput;
    }
    ICharacterId characterId = modelIdentifier.getCharacterId();
    if (viewInput != null && characterId.equals(viewInput.getCharacterId())) {
      return viewInput;
    }
    return new PointViewInput(characterId, modelExtensionPoint.getExperiencePointConfigurations(templateProvider, characterId));
  }
}