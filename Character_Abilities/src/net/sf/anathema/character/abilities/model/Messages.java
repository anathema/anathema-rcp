package net.sf.anathema.character.abilities.model;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.abilities.model.messages"; //$NON-NLS-1$
  public static String DefaultAbilityGroups_Life;
  public static String DefaultAbilityGroups_War;
  public static String DefaultAbilityGroups_Wisdom;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}