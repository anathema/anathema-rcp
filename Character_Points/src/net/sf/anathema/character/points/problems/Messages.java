package net.sf.anathema.character.points.problems;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.points.problems.messages"; //$NON-NLS-1$
  public static String BonusPointProblem_ModelName;
  public static String BonusPointProblem_Overspent;
  public static String BonusPointProblem_Underspent;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
