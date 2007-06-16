package net.sf.anathema.basics.repository.messages;

import org.eclipse.osgi.util.NLS;

public class BasicRepositoryMessages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.repository.messages.basicRepositoryMessages"; //$NON-NLS-1$
  public static String RepositoryBasics_ProjectMemberRetrievingErrorMessage;
  public static String RepositoryBasics_CreateEditorInputFailedMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, BasicRepositoryMessages.class);
  }

  private BasicRepositoryMessages() {
    // nothing to do
  }
}