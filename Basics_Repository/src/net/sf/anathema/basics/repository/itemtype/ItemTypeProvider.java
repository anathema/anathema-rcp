package net.sf.anathema.basics.repository.itemtype;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.itemtype.internal.ItemType;

public class ItemTypeProvider {

  private static final String ITEMTYPES_EXTENSION_POINT = "itemtypes"; //$NON-NLS-1$
  private final IExtensionProvider configurationProvider;

  public ItemTypeProvider() {
    this.configurationProvider = new EclipseExtensionPoint(RepositoryPlugin.ID, ITEMTYPES_EXTENSION_POINT);
  }

  public Iterable<IItemType> getItemTypes() {
    List<IItemType> itemTypes = new ArrayList<IItemType>();
    for (IPluginExtension pluginExtension : configurationProvider.getExtensions()) {
      for (IExtensionElement extensionElement : pluginExtension.getElements()) {
        itemTypes.add(new ItemType(pluginExtension.getContributorId(), extensionElement));
      }
    }
    return itemTypes;
  }

  public IItemType getById(String id) {
    for (IItemType type : getItemTypes()) {
      if (type.getId().equals(id)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No item type found for id " + id); //$NON-NLS-1$
  }
}