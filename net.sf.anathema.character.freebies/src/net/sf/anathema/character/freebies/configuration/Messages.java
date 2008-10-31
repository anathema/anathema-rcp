package net.sf.anathema.character.freebies.configuration;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.freebies.configuration.messages"; //$NON-NLS-1$
  public static String CreditManager_ErrorInstantiatingHandler;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}