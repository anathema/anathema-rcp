package net.sf.anathema.character.points.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.model.ICharacterId;

public class DummyCharacterId implements ICharacterId {

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