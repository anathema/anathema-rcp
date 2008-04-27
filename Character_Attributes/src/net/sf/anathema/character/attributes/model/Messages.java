package net.sf.anathema.character.attributes.model;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.attributes.model.messages"; //$NON-NLS-1$
  public static String AttributeGroup_MentalLabel;
  public static String AttributeGroup_PhysicalLabel;
  public static String AttributeGroup_SocialLabel;
  public static String AttributeGroupTemplate_MentalGroup;
  public static String AttributeGroupTemplate_PhysicalGroup;
  public static String AttributeGroupTemplate_SocialGroup;
  public static String Attributes_NotFound_Message;
  public static String AttributesEditorInput_GroupLessTraitMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}