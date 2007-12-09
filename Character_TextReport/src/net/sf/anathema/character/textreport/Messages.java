package net.sf.anathema.character.textreport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.textreport.messages"; //$NON-NLS-1$
  public static String TextEncoderExtensionPoint_ErrorMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}