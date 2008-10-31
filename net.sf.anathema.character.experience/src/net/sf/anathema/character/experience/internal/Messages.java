package net.sf.anathema.character.experience.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.experience.internal.messages"; //$NON-NLS-1$
  public static String AbstractToggleExperienceHandler_SaveExperienceInterrupted;
  public static String ToggleExperienceActionDelegate_ErrorSavingModel;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
