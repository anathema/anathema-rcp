package net.sf.anathema.character.points.view;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.points.view.messages"; //$NON-NLS-1$
  public static String CharacterPointsViewTitleFactory_ExperiencedTitle;
  public static String CharacterPointsViewTitleFactory_InexperiencedTitle;
  public static String CharacterPointsViewTitleFactory_NeutralTitle;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
