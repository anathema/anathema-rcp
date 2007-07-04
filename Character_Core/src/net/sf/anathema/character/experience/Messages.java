package net.sf.anathema.character.experience;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.experience.messages"; //$NON-NLS-1$
  public static String ToggleExperienceActionDelegate_ErrorSavingModel;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}