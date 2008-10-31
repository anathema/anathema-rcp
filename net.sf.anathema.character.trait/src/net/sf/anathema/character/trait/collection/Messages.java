package net.sf.anathema.character.trait.collection;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.trait.collection.messages"; //$NON-NLS-1$
  public static String Trait_NotFound_Message;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}