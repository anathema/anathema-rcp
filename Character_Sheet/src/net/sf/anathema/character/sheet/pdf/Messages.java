package net.sf.anathema.character.sheet.pdf;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.sheet.print.messages"; //$NON-NLS-1$
  public static String CharacterSheetHandler_CharacterPdfErrorMessage;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}