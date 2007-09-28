package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.repository.treecontent.messages"; //$NON-NLS-1$
  public static String DeleteViewElementActionDelegate_Confirm_Dialog_Message;
  public static String DeleteViewElementActionDelegate_Confirm_Dialog_Title;
  public static String ViewElementDeleter_DeleteInterruptedError;
  public static String ViewElementDeleter_Error;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
