package net.sf.anathema.character.attributes.textreport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.attributes.textreport.messages"; //$NON-NLS-1$
  public static String AttributesTextEncoder_AttributesTitle;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}