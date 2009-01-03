package net.sf.anathema.charms.character.preference;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.charms.character.preference.messages"; //$NON-NLS-1$
  public static String CharmPreferencePage_Explanation;
  public static String CharmPreferencePage_Forget;
  public static String CharmPreferencePage_Intro;
  public static String CharmPreferencePage_Remember;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
