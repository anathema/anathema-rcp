package net.sf.anathema.charms.character.sheet.stats;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.charms.character.sheet.stats.messages"; //$NON-NLS-1$
  public static String MagicCostStatsGroup_Title;
  public static String MagicDetailsStatsGroup_Empty;
  public static String MagicDetailsStatsGroup_Title;
  public static String MagicDurationStatsGroup_Title;
  public static String MagicNameStatsGroup_Title;
  public static String MagicSourceStatsGroup_Title;
  public static String MagicTypeStatsGroup_Title;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //nothing to do
  }
}
