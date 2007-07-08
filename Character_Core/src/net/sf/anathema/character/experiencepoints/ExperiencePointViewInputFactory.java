package net.sf.anathema.character.experiencepoints;

import org.eclipse.ui.IEditorPart;

public class ExperiencePointViewInputFactory {

  public ExperiencePointViewInput createEditorInput(IEditorPart topPart, ExperiencePointViewInput viewInput) {
    return new ExperiencePointViewInput();
  }
}