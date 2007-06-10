package net.sf.anathema.basics.repository.linkage;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.repository.linkage.messages"; //$NON-NLS-1$
  public static String RepositoryEditorLinkAction_LinkWithEditor;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
