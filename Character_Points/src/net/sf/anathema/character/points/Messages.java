package net.sf.anathema.character.points;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.points.messages"; //$NON-NLS-1$
  public static String MissingCalculatorPointConfigurations_PointsNA;
  public static String PointConfigurationExtensionPoint_CalculatorLoadError;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}