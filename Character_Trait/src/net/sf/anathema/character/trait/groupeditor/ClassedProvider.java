package net.sf.anathema.character.trait.groupeditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassedProvider <B> implements Iterable<B>{

  public Map<Class<?>, B> instancesByClass = new HashMap<Class<?>, B>();
  
  public void add(B object) {
    instancesByClass.put(object.getClass(), object);
  }
  
  @SuppressWarnings("unchecked")
  public <T extends B> T get(Class<T> clazz) {
    return (T) instancesByClass.get(clazz);
  }

  @Override
  public Iterator<B> iterator() {
    return new ArrayList<B>(instancesByClass.values()).iterator();
  }

  public void addAll(Collection<B> objects) {
    for (B object : objects) {
      add(object);
    }
  }
}