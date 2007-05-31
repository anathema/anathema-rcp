package net.sf.anathema.campaign.plot.creation;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.plot.creation.messages"; //$NON-NLS-1$
  public static String NewPlotElementActionDelegate_AddNewMessage;
  static {
// initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
