package net.sf.anathema.campaign.plot.report;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.note.report.messages"; //$NON-NLS-1$
  public static String MultiColumnSeriesReport_Synopsis;
  public static String MultiColumnSeriesReport_TableOfContent;
  public static String PlotExportWizard_Title;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
