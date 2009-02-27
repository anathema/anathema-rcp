package net.sf.anathema.character.spiritualtraits.textreport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.spiritualtraits.textreport.messages"; //$NON-NLS-1$
  public static String EssenceTextEncoder_Title;
  public static String EssenceTextEncoder_Trait;
  public static String VirtuesTextEncoder_Title;
  public static String WillpowerTextEncoder_Title;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}