package net.sf.anathema.character.trait.resources;

import net.sf.anathema.basics.eclipse.logging.Logger;

import org.eclipse.osgi.util.NLS;

public class ReflectionMessages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.trait.resources.messages"; //$NON-NLS-1$
  public static String ReflectionMessages_ErrorMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, ReflectionMessages.class);
  }

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
      logger.warn(NLS.bind(ReflectionMessages_ErrorMessage, fieldName, clazz), e);
      return fieldName;
    }
  }
}