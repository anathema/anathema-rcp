package net.sf.anathema.character.points;

import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;
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
    IFolder folder = modelIdentifier.getFolder();
    if (viewInput != null && folder.equals(viewInput.getFolder())) {
      return viewInput;
    }
    return new PointViewInput(folder, modelExtensionPoint.getExperiencePointConfigurations(templateProvider, folder));
  }
}