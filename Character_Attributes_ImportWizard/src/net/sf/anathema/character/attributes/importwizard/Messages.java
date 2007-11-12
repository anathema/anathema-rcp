package net.sf.anathema.character.attributes.importwizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.attributes.importwizard.messages"; //$NON-NLS-1$
  public static String AttributesImporter_ErrorMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
