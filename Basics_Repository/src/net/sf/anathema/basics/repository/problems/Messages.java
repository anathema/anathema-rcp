package net.sf.anathema.basics.repository.problems;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.repository.problems.messages"; //$NON-NLS-1$
  public static String ProblemsView_DescriptionHeader;
  public static String ProblemsView_PathHeader;
  public static String ProblemsView_Title;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
