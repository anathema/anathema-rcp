package net.sf.anathema.character.textreport.wizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.textreport.wizard.messages"; //$NON-NLS-1$
  public static String TextExportMessages_PageTitle;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}