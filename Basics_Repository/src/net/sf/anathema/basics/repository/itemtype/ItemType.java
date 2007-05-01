package net.sf.anathema.basics.repository.itemtype;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public class ItemType implements IItemType {

  private final IExtensionElement configurationNode;

  public ItemType(IExtensionElement configurationNode) {
    this.configurationNode = configurationNode;
  }

  @Override
  public String getName() {
    return configurationNode.getAttribute("type"); //$NON-NLS-1$
  }

  @Override
  public String getProjectName() {
    return configurationNode.getAttribute("project"); //$NON-NLS-1$
  }

  @Override
  public String getFileExtension() {
    return configurationNode.getAttribute("file-extension"); //$NON-NLS-1$
  }
}