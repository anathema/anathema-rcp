package net.sf.anathema.rcp;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.rcp.messages"; //$NON-NLS-1$
  
  public static String Application_LockDialogMessage;

  public static String Application_LockDialogTitle;

  public static String ApplicationActionBarAdvisor_ActionCreationError;

  public static String ApplicationActionBarAdvisor_viewMenuName;

  public static String ApplicationActionBarAdvisor_fileMenuName;
  
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}