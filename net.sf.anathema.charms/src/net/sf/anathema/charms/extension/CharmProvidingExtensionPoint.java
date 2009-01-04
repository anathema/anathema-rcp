package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.NamePredicate;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.CharmTreeProvider;
import net.sf.anathema.charms.tree.ITreeProvider;

public class CharmProvidingExtensionPoint {
  private static final String TAG_TREE_PROVIDER = "treeProvider"; //$NON-NLS-1$
  private static final String TAG_CHARM_DATA_MAP = "charmDataMap"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_CHARMPROVIDING = "charmproviding"; //$NON-NLS-1$

  private static EclipseExtensionPoint createExtensionPoint() {
    return new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT_CHARMPROVIDING);
  }

  public static ICharmDataMap CreateCharmDataMap() {
    EclipseExtensionPoint extensionPoint = createExtensionPoint();
    NamePredicate dataMapPredicate = NamePredicate.ForElementName(TAG_CHARM_DATA_MAP);
    ClassConveyerBelt<ICharmDataMap> conveyerBelt = new ClassConveyerBelt<ICharmDataMap>(
        extensionPoint,
        ICharmDataMap.class,
        dataMapPredicate);
    return new CharmDataMap(conveyerBelt.getAllObjects());
  }

  public static ITreeProvider CreateTreeProvider() {
    EclipseExtensionPoint extensionPoint = createExtensionPoint();
    NamePredicate treeProviderPredicate = NamePredicate.ForElementName(TAG_TREE_PROVIDER);
    ClassConveyerBelt<ITreeProvider> conveyerBelt = new ClassConveyerBelt<ITreeProvider>(
        extensionPoint,
        ITreeProvider.class,
        treeProviderPredicate);
    return new CharmTreeProvider(conveyerBelt.getAllObjects());
  }
}