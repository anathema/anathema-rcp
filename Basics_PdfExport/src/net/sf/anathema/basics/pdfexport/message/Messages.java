package net.sf.anathema.basics.pdfexport.message;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.pdfexport.messages"; //$NON-NLS-1$
  public static String SheetExportMessages_Description;
  public static String SheetExportMessages_OpenLabel;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}