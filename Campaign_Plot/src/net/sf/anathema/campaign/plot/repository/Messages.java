package net.sf.anathema.campaign.plot.repository;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.plot.repository.messages"; //$NON-NLS-1$
  public static String DeletePlotElementActionDelegate_Confirm_Dialog_Message;
  public static String DeletePlotElementActionDelegate_Confirm_Dialog_Title;
  public static String DeletePlotElementActionDelegate_DeleteActionText_Message;
  public static String DeletePlotElementActionDelegate_Deletion_Error;
  public static String PlotElementViewElement_UntitledElementNameMessage;
  public static String PlotViewElementFactory_LoadingPlotErrorMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
