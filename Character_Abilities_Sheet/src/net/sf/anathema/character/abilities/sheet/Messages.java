package net.sf.anathema.character.abilities.sheet;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.abilities.sheet.messages"; //$NON-NLS-1$
  public static String AbilitiesEncoder_AbilitiesHeader;
  public static String AbilitiesEncoder_CraftsHeader;
  public static String AbilitiesEncoder_MarkerComment;
  public static String AbilitiesEncoder_SpecialtiesHeader;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}