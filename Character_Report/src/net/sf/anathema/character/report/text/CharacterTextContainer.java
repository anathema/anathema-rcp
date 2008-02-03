package net.sf.anathema.character.report.text;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.report.plugin.IReportPluginConstants;

public class CharacterTextContainer {

  private final IPluginExtension[] extensions;

  public CharacterTextContainer() {
    this(new EclipseExtensionPoint(IReportPluginConstants.PLUGIN_ID, "texts").getExtensions()); //$NON-NLS-1$
  }

  public CharacterTextContainer(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  public ICharacterText getText(String id) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getAttribute("id").equals(id)) { //$NON-NLS-1$
          try {
            return element.getAttributeAsObject("class", ICharacterText.class); //$NON-NLS-1$
          }
          catch (ExtensionException e) {
            Logger logger = new Logger(IReportPluginConstants.PLUGIN_ID);
            logger.error("Could not instantiate object for extension point", e);
            return null;
          }
        }
      }
    }
    return null;
  }
}