package net.sf.anathema.character.core.model;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.model.messages"; //$NON-NLS-1$
  public static String DependenciesHandler_Error;
  public static String ModelCache_ModelLoadError_Format;
  public static String ModelCache_ModelNotFound_Message;
  public static String ModelExtensionPoint_ModelDescriptionLoadErrorMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}