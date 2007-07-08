package net.sf.anathema.character.experiencepoints;

import org.eclipse.core.resources.IFolder;

public interface IExperiencePointViewInput {

  public IExperiencePointEntry[] getEntries();

  public IFolder getFolder();
}