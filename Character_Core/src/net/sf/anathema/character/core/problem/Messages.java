package net.sf.anathema.character.core.problem;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.problem.messages"; //$NON-NLS-1$
  public static String UneditedModelProblem_Description;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}