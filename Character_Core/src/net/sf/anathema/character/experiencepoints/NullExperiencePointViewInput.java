package net.sf.anathema.character.experiencepoints;

import org.eclipse.core.resources.IFolder;

public class NullExperiencePointViewInput implements IExperiencePointViewInput {

  private IExperiencePointEntry[] experiencePointEntries = new IExperiencePointEntry[0];

  @Override
  public IExperiencePointEntry[] createEntries() {
    return experiencePointEntries;
  }

  @Override
  public IFolder getFolder() {
    return null;
  }
}