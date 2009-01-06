package net.sf.anathema.charms.textencoder;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.charms.textencoder.messages"; //$NON-NLS-1$
  public static String GenericCharmTextEncoder_FinalConjunction;
  public static String GenericCharmTextEncoder_Intro;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //nothing to do
  }
}
