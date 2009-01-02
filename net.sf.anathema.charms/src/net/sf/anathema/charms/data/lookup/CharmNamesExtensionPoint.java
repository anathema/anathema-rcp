package net.sf.anathema.charms.data.lookup;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmWithId;
import net.sf.anathema.charms.data.INameMap;

public class CharmNamesExtensionPoint implements INameMap {
  private static final String EXTENSION_NAME = "charmname"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private final EclipseExtensionPoint extensionPoint;

  public CharmNamesExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_NAME));
  }

  public CharmNamesExtensionPoint(EclipseExtensionPoint eclipseExtensionPoint) {
    this.extensionPoint = eclipseExtensionPoint;
  }

  public String getNameFor(String charmId) {
    IExtensionElement charm = extensionPoint.getFirst(new CharmWithId(charmId));
    if (charm==null) {
      return charmId;
    }
    return charm.getAttribute(ATTRIB_NAME);
  }
}