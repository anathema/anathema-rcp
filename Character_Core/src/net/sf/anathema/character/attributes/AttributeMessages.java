package net.sf.anathema.character.attributes;

import net.sf.anathema.character.core.CharacterCorePlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class AttributeMessages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.attributes.messages"; //$NON-NLS-1$
  public static String Strength;
  public static String Dexterity;
  public static String Stamina;
  public static String Charisma;
  public static String Appearance;
  public static String Manipulation;
  public static String Intelligence;
  public static String Perception;
  public static String Wits;
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
      CharacterCorePlugin.getDefaultInstance().log(IStatus.WARNING, "No name defined for Attribute" + attributeId, e);
      return attributeId;
    }
  }
}
