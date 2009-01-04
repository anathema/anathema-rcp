package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.trait.resources.ReflectionMessages;
import net.sf.anathema.charms.IPluginConstants;

import org.eclipse.osgi.util.NLS;

public class ResourceMessages extends NLS  {
    private static final String BUNDLE_NAME = "net.sf.anathema.charms.view.tooltipp.resourcemessages"; //$NON-NLS-1$
    
    public static String motes;
    public static String willpower;
    public static String bashing_hl;
    public static String lethal_hl;
    public static String aggravated_hl;
    public static String experience;
    
    private static ReflectionMessages MESSAGES = new ReflectionMessages(ResourceMessages.class, new Logger(IPluginConstants.PLUGIN_ID));
    static {
      NLS.initializeMessages(BUNDLE_NAME, ResourceMessages.class);
    }

    public static String get(String id) {
      return MESSAGES.get(id);
    }
}