package net.sf.anathema.character.core.repository;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.repository.messages"; //$NON-NLS-1$
  public static String CharacterViewElement_ModelLoadError;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}