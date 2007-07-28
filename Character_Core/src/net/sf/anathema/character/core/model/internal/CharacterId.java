package net.sf.anathema.character.core.model.internal;

import net.disy.commons.core.util.ObjectUtilities;
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
  
  @Override
  public String toString() {
    return folder.getName();
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CharacterId) ) {
      return false;
    }
    CharacterId other = (CharacterId) obj;
    return folder.equals(other.folder);
  }
  
  @Override
  public int hashCode() {
    return ObjectUtilities.getHashCode(folder);
  }
}