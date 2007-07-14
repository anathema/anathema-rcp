package net.sf.anathema.character.core.model.internal;

import net.sf.anathema.basics.eclipse.resource.FileContentHandle;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.model.ICharacterId;

import org.eclipse.core.resources.IFolder;

public class CharacterId implements ICharacterId {

  private final IFolder folder;

  public CharacterId(IFolder folder) {
    this.folder = folder;
  }

  @Override
  public IContentHandle getContents(String fileName) {
    return new FileContentHandle(folder.getFile(fileName));
  }
}