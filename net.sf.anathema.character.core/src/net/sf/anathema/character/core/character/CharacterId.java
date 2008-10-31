package net.sf.anathema.character.core.character;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.eclipse.resource.FileContentHandle;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.Path;

public class CharacterId extends DefaultAdaptable implements ICharacterId {

  private final IContainer folder;

  public CharacterId(IContainer folder) {
    this.folder = folder;
    set(IContainer.class, folder);
  }

  @Override
  public IContentHandle getContents(String fileName) {
    return new FileContentHandle(folder.getFile(new Path(fileName)));
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