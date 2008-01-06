package net.sf.anathema.character.caste.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.caste.persistence.ICasteProvider;
import net.sf.anathema.character.caste.plugin.ICastePluginConstants;

public class CasteProvider implements ICasteProvider {

  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private IPluginExtension[] pluginExtensions;

  public CasteProvider() {
    this.pluginExtensions = new EclipseExtensionPoint(ICastePluginConstants.PLUGIN_ID, "castes").getExtensions(); //$NON-NLS-1$
  }

  @Override
  public ICaste[] getCastes(String typeId) {
    List<ICaste> allCastes = new ArrayList<ICaste>();
    for (IPluginExtension extension : pluginExtensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getAttribute(ATTRIB_CHARACTER_TYPE).equals(typeId)) {
          for (IExtensionElement child : element.getElements()) {
            allCastes.add(new Caste(child));
          }
        }
      }
    }
    return allCastes.toArray(new ICaste[allCastes.size()]);
  }
}