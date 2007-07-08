package net.sf.anathema.character.experiencepoints;

import org.eclipse.ui.IEditorPart;

public class ExperiencePointViewInputFactory {

  private static final NullExperiencePointViewInput nullInput = new NullExperiencePointViewInput();

  public IExperiencePointViewInput createEditorInput(IEditorPart topPart, IExperiencePointViewInput viewInput) {
    if (topPart == null) {
      return nullInput;
    }
    return new ExperiencePointViewInput();
  }
}