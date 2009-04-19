package net.sf.anathema.character.trait.resources;

import java.lang.reflect.Field;

import net.sf.anathema.basics.eclipse.logging.Logger;

import org.eclipse.osgi.util.NLS;

public class ReflectionMessages {

  private final Class< ? > clazz;
  private final Logger logger;

  public ReflectionMessages(Class< ? > clazz, Logger logger) {
    this.clazz = clazz;
    this.logger = logger;
  }

  public String get(String fieldName) {
    try {
      return (String) clazz.getField(fieldName).get(null);
    }
    catch (Exception e) {
      logger.warn(NLS.bind(Messages.ReflectionMessages_ErrorMessage, fieldName, clazz), e);
      return fieldName;
    }
  }

  public boolean hasField(String fieldName) {
    for (Field field : clazz.getDeclaredFields()) {
      if (field.getName().equals(fieldName)) {
        return true;
      }
    }
    return false;
  }
}