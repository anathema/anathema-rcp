package net.sf.anathema.character.description.sheet;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.sheet.description.messages"; //$NON-NLS-1$
  public static String PersonalInfoEncoder_CasteLabel;
  public static String PersonalInfoEncoder_ConceptLabel;
  public static String PersonalInfoEncoder_EncoderTitle;
  public static String PersonalInfoEncoder_MotivationLabel;
  public static String PersonalInfoEncoder_PlayerLabel;
  public static String PersonalInfoEncoder_TemplateLabel;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
