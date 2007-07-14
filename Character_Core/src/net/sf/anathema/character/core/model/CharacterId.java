package net.sf.anathema.character.core.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public class CharacterId implements ICharacterId {

  private final IFolder folder;

  public CharacterId(IFolder folder) {
    this.folder = folder;
  }

  @Override
  public IFile getContents(String fileName) {
    return folder.getFile(fileName);
  }
}