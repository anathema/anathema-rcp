package net.sf.anathema.character.core.editors;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.editors.messages"; //$NON-NLS-1$
  public static String ModelPersistableFactory_CharacterRestorationErrorMessage;
  public static String ModelPersistableFactory_NoTemplateAvailableMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}