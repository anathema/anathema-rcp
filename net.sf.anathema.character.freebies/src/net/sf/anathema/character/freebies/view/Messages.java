package net.sf.anathema.character.freebies.view;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.freebies.view.messages"; //$NON-NLS-1$
  public static String FreebiesValueEntry_EntryMessageFormat;
  public static String FreebiesValueEntry_MissingEntry;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}