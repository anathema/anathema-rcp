package net.sf.anathema.character.backgrounds;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.backgrounds.messages"; //$NON-NLS-1$
  public static String BackgroundConfiguration_Error_No_Groups;
  public static String BackgroundEditor_HINT_TEXT;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }
}