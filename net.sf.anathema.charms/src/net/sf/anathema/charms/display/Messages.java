package net.sf.anathema.charms.display;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.charms.display.messages"; //$NON-NLS-1$
  public static String DisplayResource_BaseAmountPattern;
  public static String DisplayResource_LinearAmountPattern;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
