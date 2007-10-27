package net.sf.anathema.basics.swt.file;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.swt.file.messages"; //$NON-NLS-1$
  public static String FileChoosing_Character;
  public static String FileChoosing_Legacy_Note_Filter_Description;
  public static String FileChoosing_PDF_Filter_Description;
  public static String PdfFileOutputStreamFactory_SaveDialogMessage;
  public static String PdfFileOutputStreamFactory_SaveDialogTitle;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
