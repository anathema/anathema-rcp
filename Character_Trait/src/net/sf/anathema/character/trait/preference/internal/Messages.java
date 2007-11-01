package net.sf.anathema.character.trait.preference.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.trait.preference.internal.messages"; //$NON-NLS-1$
  public static String TraitPreferencePage_ExperienceTreatmentExplanation;
  public static String TraitPreferencePage_ExperienceTreatmentIncrease;
  public static String TraitPreferencePage_ExperienceTreatmentIntro;
  public static String TraitPreferencePage_ExperienceTreatmentUnchanged;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}