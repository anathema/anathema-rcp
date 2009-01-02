package net.sf.anathema.character.attributes.model;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.trait.resources.ReflectionMessages;

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
  private static ReflectionMessages MESSAGES = new ReflectionMessages(AttributeMessages.class, new Logger(AttributesPlugin.ID));
  static {
    NLS.initializeMessages(BUNDLE_NAME, AttributeMessages.class);
  }

  private AttributeMessages() {
    // nothing to do
  }

  public static String get(String attributeId) {
    return MESSAGES.get(attributeId);
  }
}