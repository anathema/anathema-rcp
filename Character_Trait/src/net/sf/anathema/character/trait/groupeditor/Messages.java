package net.sf.anathema.character.trait.groupeditor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.trait.groupeditor.messages"; //$NON-NLS-1$
  public static String TraitGroupEditorDecorationFactory_ErrorMessageLoadingDecoration;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}