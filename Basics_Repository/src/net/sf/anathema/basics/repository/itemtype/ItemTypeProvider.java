package net.sf.anathema.basics.repository.itemtype;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.repository.itemtype.internal.ItemType;

public class ItemTypeProvider {

  private static final String REPOSITORY_ITEMTYPES = "net.sf.anathema.repository.itemtypes"; //$NON-NLS-1$
  private final IExtensionProvider configurationProvider;

  public ItemTypeProvider() {
    this(new EclipseExtensionProvider());
  }

  public ItemTypeProvider(IExtensionProvider configurationProvider) {
    this.configurationProvider = configurationProvider;
  }

  public Iterable<IItemType> getItemTypes() {
    List<IItemType> itemTypes = new ArrayList<IItemType>();
    for (IPluginExtension pluginExtension : configurationProvider.getExtensions(REPOSITORY_ITEMTYPES)) {
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