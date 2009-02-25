package net.sf.anathema.character.spiritualtraits.model;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.spiritualtraits.model.messages"; //$NON-NLS-1$
  public static String SpiritualTraitGroupTemplate_WillpowerGroup;
  public static String SpiritualTraitGroupTemplate_VirtuesGroup;
  public static String SpiritualTraitGroupTemplate_EssenceGroup;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}