package net.sf.anathema.charms.character.sheet.generic;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.charms.character.sheet.generic.messages"; //$NON-NLS-1$
  public static String CategoryNames_Ability;
  public static String CategoryNames_Attribute;
  public static String GenericCharmEncoder_Header;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //nothing to do
  }
}
