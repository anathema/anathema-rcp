package net.sf.anathema.character.core.create;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.create.messages"; //$NON-NLS-1$
  public static String NewCharacterActionDelegate_CharacterCreationError;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}