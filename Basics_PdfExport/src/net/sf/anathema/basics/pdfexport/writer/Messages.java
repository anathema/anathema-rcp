package net.sf.anathema.basics.pdfexport.writer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.pdfexport.writer.messages"; //$NON-NLS-1$
  public static String AbstractReportRunnable_preparingItemSubtask;
  public static String AbstractReportRunnable_taskName;
  public static String ReportRunner_PdfErrorMessage;
  public static String ReportRunner_FileInUseMessage;
  public static String ReportRunner_Title;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}