package net.sf.anathema.character.experiencepoints;

import org.eclipse.core.resources.IFolder;

public interface IPointViewInput {

  public IPointEntry[] createEntries();

  public IFolder getFolder();
}