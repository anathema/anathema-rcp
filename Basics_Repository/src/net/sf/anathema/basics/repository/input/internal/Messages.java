package net.sf.anathema.basics.repository.input.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.repository.input.internal.messages"; //$NON-NLS-1$
  public static String FileItemEditorInputFactory_FileNotFoundMessage;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
