package net.sf.anathema.character.freebies.attributes.mark;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.freebies.attributes.mark.messages"; //$NON-NLS-1$
  public static String FavoredAttributeFreebiesMarker_UnspentFreebies;
  public static String AttributeGroupModelMarker_UnspentPrimaryDescription;
  public static String AttributeGroupModelMarker_UnspentSecondaryDescription;
  public static String AttributeGroupModelMarker_UnspentTertiaryDescription;
  public static String FavoredAttributePicksMarker_UnspentPicks;
  public static String ResourceModelMarker_ErrorWhileMarking;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
