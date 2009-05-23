package net.sf.anathema.character.core.evaluation;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.properties.IProperty;
import net.sf.anathema.character.core.properties.IPropertyMap;

public class DummyPropertyMap implements IPropertyMap {

  public final Map<String, IProperty> propertiesById = new HashMap<String, IProperty>();

  @Override
  public IProperty getProperty(String id) {
    return propertiesById.get(id);
  }
}