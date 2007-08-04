package net.sf.anathema.character.core.model.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.model.messages"; //$NON-NLS-1$
  public static String ModelCache_ModelLoadError;
  public static String ModelCache_ModelNotFound_Message;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}