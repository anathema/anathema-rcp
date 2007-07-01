package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.attributes.AttributesPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class AttributeMessages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.attributes.model.attributemessages"; //$NON-NLS-1$
  public static String Strength;
  public static String Dexterity;
  public static String Stamina;
  public static String Charisma;
  public static String Appearance;
  public static String Manipulation;
  public static String Intelligence;
  public static String Perception;
  public static String Wits;
  public static String Physical;
  public static String Social;
  public static String Mental;
  static {
    NLS.initializeMessages(BUNDLE_NAME, AttributeMessages.class);
  }

  private AttributeMessages() {
    // nothing to do
  }

  public static String get(String attributeId) {
    try {
      return (String) AttributeMessages.class.getField(attributeId).get(null);
    }
    catch (Exception e) {
      AttributesPlugin.getDefaultInstance().log(
          IStatus.WARNING,
          NLS.bind(Messages.AttributeMessages_I18nFailed, attributeId),
          e);
      return attributeId;
    }
  }
}
