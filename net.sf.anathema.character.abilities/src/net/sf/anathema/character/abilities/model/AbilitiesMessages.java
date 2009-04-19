package net.sf.anathema.character.abilities.model;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.trait.resources.INameCollection;
import net.sf.anathema.character.trait.resources.ReflectionMessages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.osgi.util.NLS;

public class AbilitiesMessages extends NLS implements INameCollection {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.abilities.model.abilitiesmessages"; //$NON-NLS-1$
  public static String Archery;
  public static String Athletics;
  public static String Awareness;
  public static String Bureaucracy;
  public static String Craft;
  public static String Dodge;
  public static String Integrity;
  public static String Investigation;
  public static String Larceny;
  public static String Linguistics;
  public static String Lore;
  public static String MartialArts;
  public static String Medicine;
  public static String Melee;
  public static String Occult;
  public static String Performance;
  public static String Presence;
  public static String Resistance;
  public static String Ride;
  public static String Sail;
  public static String Socialize;
  public static String Stealth;
  public static String Survival;
  public static String Thrown;
  public static String War;

  private static final ReflectionMessages MESSAGES = new ReflectionMessages(AbilitiesMessages.class, new Logger(
      IAbilitiesPluginConstants.PLUGIN_ID));
  static {
    NLS.initializeMessages(BUNDLE_NAME, AbilitiesMessages.class);
  }

  @Override
  public String getName(String id) {
    return get(id);
  }

  public static String get(String id) {
    return MESSAGES.get(id);
  }

  @Override
  public boolean knowsNameFor(String traitId) {
    return MESSAGES.hasField(traitId);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}