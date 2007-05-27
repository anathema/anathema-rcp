package net.sf.anathema.basics.repository.treecontent.itemtype;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.repository.treecontent.itemtype.messages"; //$NON-NLS-1$
  public static String PrintNameProvider_errorReadingDisplayNameMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}