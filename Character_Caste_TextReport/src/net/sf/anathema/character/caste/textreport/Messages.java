package net.sf.anathema.character.caste.textreport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.caste.textreport.messages"; //$NON-NLS-1$
  public static String CasteTextReportEncoder_CasteLabel;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}