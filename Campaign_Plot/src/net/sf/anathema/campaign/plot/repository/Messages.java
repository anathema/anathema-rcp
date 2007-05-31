package net.sf.anathema.campaign.plot.repository;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.plot.repository.messages"; //$NON-NLS-1$
  public static String PlotUnit_Plot;
  public static String PlotUnit_Story;
  public static String PlotUnit_Episode;
  public static String PlotUnit_Scene;
  public static String PlotViewElementFactory_LoadingPlotErrorMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
