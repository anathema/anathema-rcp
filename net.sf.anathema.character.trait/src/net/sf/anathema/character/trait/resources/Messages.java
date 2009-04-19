package net.sf.anathema.character.trait.resources;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.trait.resources.messages"; //$NON-NLS-1$
  public static String TraitMessages_Error;
  public static String ReflectionMessages_ErrorMessage;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
