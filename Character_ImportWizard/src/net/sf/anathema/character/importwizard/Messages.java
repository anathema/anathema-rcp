package net.sf.anathema.character.importwizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.importwizard.messages"; //$NON-NLS-1$
  public static String CharacterImportMessages_Description;
  public static String CharacterImportMessages_OpenMessage;
  public static String CharacterImportMessages_PageName;
  public static String CharacterImportWizard_ConversionError;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
