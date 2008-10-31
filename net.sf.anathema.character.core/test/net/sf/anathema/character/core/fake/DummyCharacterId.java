package net.sf.anathema.character.core.fake;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.character.core.character.ICharacterId;

public class DummyCharacterId extends DefaultAdaptable implements ICharacterId {

  private final Map<String, IContentHandle> contentHandlesByName = new HashMap<String, IContentHandle>();

  @Override
  public IContentHandle getContents(String fileName) {
    return contentHandlesByName.get(fileName);
  }

  public void addContentHandle(String name, IContentHandle handle) {
    contentHandlesByName.put(name, handle);
  }

  @Override
  public boolean equals(Object arg0) {
    return arg0 instanceof DummyCharacterId;
  }
}