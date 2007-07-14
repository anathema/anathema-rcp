package net.sf.anathema.character.experiencepoints;

import org.eclipse.core.resources.IFolder;

public class NullExperiencePointViewInput implements IPointViewInput {

  private IPointEntry[] experiencePointEntries = new IPointEntry[0];

  @Override
  public IPointEntry[] createEntries() {
    return experiencePointEntries;
  }

  @Override
  public IFolder getFolder() {
    return null;
  }
}