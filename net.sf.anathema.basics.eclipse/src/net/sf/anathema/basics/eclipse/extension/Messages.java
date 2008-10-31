package net.sf.anathema.basics.eclipse.extension;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.eclipse.extension.messages"; //$NON-NLS-1$
  public static String ClassConveyerBelt_InstantiationException;
  public static String EclipseExtensionProvider_ExtensionPointNotFoundMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}