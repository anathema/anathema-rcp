package net.sf.anathema.charms.character.sheet;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.charms.character.sheet.messages"; //$NON-NLS-1$
  public static String CharmEncoder_Title;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //nothing to do
  }
}
