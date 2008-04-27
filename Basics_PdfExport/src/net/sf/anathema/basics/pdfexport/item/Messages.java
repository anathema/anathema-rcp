package net.sf.anathema.basics.pdfexport.item;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.pdfexport.item.messages"; //$NON-NLS-1$
  public static String ExportItemDialogPage_Message;
  public static String ExportItemDialogPage_Title;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}