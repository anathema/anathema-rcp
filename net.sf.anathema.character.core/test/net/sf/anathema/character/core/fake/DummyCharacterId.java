package net.sf.anathema.character.core.fake;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.character.core.character.ICharacterId;

@SuppressWarnings("nls")
public class DummyCharacterId extends DefaultAdaptable implements ICharacterId {

  public String equalsIdentifier = "DummyCharacterId";
  public final Map<String, IContentHandle> contentHandlesByName = new HashMap<String, IContentHandle>();

  @Override
  public IContentHandle getContents(String fileName) {
    return contentHandlesByName.get(fileName);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof DummyCharacterId)) {
      return false;
    }
    DummyCharacterId other = (DummyCharacterId) object;
    return other.equalsIdentifier.equals(equalsIdentifier);
  }

  @Override
  public int hashCode() {
    return equalsIdentifier.hashCode();
  }
}