package net.sf.anathema.character.abilities.textreport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.abilities.textreport.messages"; //$NON-NLS-1$
  public static String AbilitiesTextEncoder_Title;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}