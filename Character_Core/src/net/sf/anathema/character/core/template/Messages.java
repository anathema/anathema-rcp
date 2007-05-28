package net.sf.anathema.character.core.template;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.template.messages"; //$NON-NLS-1$
  public static String CharacterTemplateProvider_NoTemplateReferenceMessage;

  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
