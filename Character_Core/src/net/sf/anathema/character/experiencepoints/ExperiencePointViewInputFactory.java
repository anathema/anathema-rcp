package net.sf.anathema.character.experiencepoints;

import net.sf.anathema.character.core.model.IModelIdentifier;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class ExperiencePointViewInputFactory {

  private static final NullExperiencePointViewInput nullInput = new NullExperiencePointViewInput();

  public IExperiencePointViewInput createEditorInput(IEditorPart topPart, IExperiencePointViewInput viewInput) {
    if (topPart == null) {
      return nullInput;
    }
    IEditorInput editorInput = topPart.getEditorInput();
    IModelIdentifier modelIdentifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      return nullInput;
    }
    if (viewInput != null && modelIdentifier.getFolder().equals(viewInput.getFolder())) {
      return viewInput;
    }
    return new ExperiencePointViewInput(modelIdentifier.getFolder());
  }
}