package net.sf.anathema.basics.eclipse.runtime;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.provider.IProvider;
import net.disy.commons.core.provider.StaticProvider;

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
      return objectProvidersByClass.get(adapter).getObject();
    }
    for (Class<?> clazz : objectProvidersByClass.keySet()) {
      if (clazz.isAssignableFrom(adapter)) {
        Object object = objectProvidersByClass.get(clazz).getObject();
        if (adapter.isInstance(object)) {
          return object;
        }
      }
    }
    return null;
  }

  public <T> void set(Class<T> clazz, IProvider<T> provider) {
    objectProvidersByClass.put(clazz, provider);
  }

  public <T> void set(Class<T> clazz, final T object) {
    set(clazz, new StaticProvider<T>(object));
  }
}