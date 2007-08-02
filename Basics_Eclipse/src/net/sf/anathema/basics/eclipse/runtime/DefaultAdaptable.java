package net.sf.anathema.basics.eclipse.runtime;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;

public class DefaultAdaptable implements IAdaptable {

  private final Map<Class<?>, IProvider<?>> objectProvidersByClass = new HashMap<Class<?>, IProvider<?>>();
  
  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return getObjectFor(adapter);
  }

  private Object getObjectFor(Class<?> adapter) {
    if (objectProvidersByClass.containsKey(adapter)) {
      return objectProvidersByClass.get(adapter).get();
    }
    for (Class<?> clazz : objectProvidersByClass.keySet()) {
      if (clazz.isAssignableFrom(adapter)) {
        Object object = objectProvidersByClass.get(clazz).get();
        if (adapter.isInstance(object)) {
          return object;
        }
      }
    }
    return null;
  }

  public <T> void add(Class<T> clazz, IProvider<T> provider) {
    objectProvidersByClass.put(clazz, provider);
  }
}