package net.sf.anathema.basics.pdfexport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.pdfexport.messages"; //$NON-NLS-1$
  public static String AbstractPdfExportWizard_FileExistsBox_Question;
  public static String AbstractPdfExportWizard_FileExistsBox_Title;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}