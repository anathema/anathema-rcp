package net.sf.anathema.basics.repository.itemtype.internal;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.IItemTypeViewElementFactory;

public class ItemType implements IItemType {

  private final IExtensionElement configurationNode;
  private final String pluginId;

  public ItemType(String pluginId, IExtensionElement configurationNode) {
    this.pluginId = pluginId;
    this.configurationNode = configurationNode;
  }

  @Override
  public String getName() {
    return getAttribute("type"); //$NON-NLS-1$
  }

  @Override
  public String getProjectName() {
    return getAttribute("project"); //$NON-NLS-1$
  }

  @Override
  public String getFileExtension() {
    return getAttribute("file-extension"); //$NON-NLS-1$
  }

  @Override
  public URL getIconUrl() {
    String iconPath = getAttribute("icon"); //$NON-NLS-1$
    return ResourceUtils.getResourceUrl(pluginId, iconPath);
  }

  private String getAttribute(String name) {
    return configurationNode.getAttribute(name);
  }

  @Override
  public String getId() {
    return getAttribute("id"); //$NON-NLS-1$
  }

  @Override
  public String getUntitledName() {
    return getAttribute("untitledName"); //$NON-NLS-1$
  }

  @Override
  public IItemTypeViewElementFactory getViewElementFactory() throws ExtensionException {
    IItemTypeViewElementFactory factory = configurationNode.getAttributeAsObject("viewElementFactoryClass", //$NON-NLS-1$
        IItemTypeViewElementFactory.class);
    factory.setItemType(this);
    return factory;
  }
}