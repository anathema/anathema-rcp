package net.sf.anathema.character.experiencepoints;

import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class PointViewInputFactory {

  private static final NullExperiencePointViewInput nullInput = new NullExperiencePointViewInput();

  public IPointViewInput createEditorInput(IEditorPart topPart, IPointViewInput viewInput) {
    if (topPart == null) {
      return nullInput;
    }
    IEditorInput editorInput = topPart.getEditorInput();
    IModelIdentifier modelIdentifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      return nullInput;
    }
    IFolder folder = modelIdentifier.getFolder();
    if (viewInput != null && folder.equals(viewInput.getFolder())) {
      return viewInput;
    }
    return new PointViewInput(folder, new ModelExtensionPoint().getExperiencePointConfigurations(
        new CharacterTemplateProvider(),
        folder));
  }
}