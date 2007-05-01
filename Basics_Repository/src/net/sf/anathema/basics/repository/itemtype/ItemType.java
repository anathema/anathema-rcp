package net.sf.anathema.basics.repository.itemtype;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public class ItemType implements IItemType {

  private final IExtensionElement configurationNode;

  public ItemType(IExtensionElement configurationNode) {
    this.configurationNode = configurationNode;
  }

  @Override
  public String getName() {
    return getFolderName();
  }

  @Override
  public String getFolderName() {
    return configurationNode.getAttribute("folder"); //$NON-NLS-1$
  }
}