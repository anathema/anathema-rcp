package net.sf.anathema.basics.repository.problems;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.repository.problems.messages"; //$NON-NLS-1$
  public static String MarkerProblem_NoDescription;
  public static String MarkerProblem_NoPath;
  public static String ProblemOpenListener_ErrorMessage;
  public static String ProblemsContentProvider_ProvoiderLoadErrorMessage;
  public static String ProblemsView_DescriptionHeader;
  public static String ProblemsView_PathHeader;
  public static String ProblemsView_Title;
  public static String ResourceEditorOpenerExtensionPoint_ErrorMessage;
  public static String ResourceProblemProvider_ErrorMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}