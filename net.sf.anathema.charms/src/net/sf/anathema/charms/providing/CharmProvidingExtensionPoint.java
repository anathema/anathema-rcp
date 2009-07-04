package net.sf.anathema.charms.providing;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.NamePredicate;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmDataMap;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.data.IExecutableCharmDataMap;
import net.sf.anathema.charms.traits.CharmTraitMap;
import net.sf.anathema.charms.traits.ICharmTraitMap;
import net.sf.anathema.charms.traits.IExecutableCharmTraitMap;
import net.sf.anathema.charms.tree.CharmTreeProvider;
import net.sf.anathema.charms.tree.IExecutableTreeProvider;
import net.sf.anathema.charms.tree.ITreeProvider;

public class CharmProvidingExtensionPoint {
  private static final String TAG_TREE_PROVIDER = "treeProvider"; //$NON-NLS-1$
  private static final String TAG_CHARM_DATA_MAP = "charmDataMap"; //$NON-NLS-1$
  private static final String TAG_CHARM_TRAITS = "charmTraits"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_CHARMPROVIDING = "charmproviding"; //$NON-NLS-1$

  private static EclipseExtensionPoint createExtensionPoint() {
    return new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT_CHARMPROVIDING);
  }

  public static ICharmDataMap CreateCharmDataMap() {
    EclipseExtensionPoint extensionPoint = createExtensionPoint();
    NamePredicate dataMapPredicate = NamePredicate.ForElementName(TAG_CHARM_DATA_MAP);
    ClassConveyerBelt<IExecutableCharmDataMap> conveyerBelt = new ClassConveyerBelt<IExecutableCharmDataMap>(
        extensionPoint,
        IExecutableCharmDataMap.class,
        dataMapPredicate);
    return new CharmDataMap(conveyerBelt.getAllObjects());
  }

  public static ITreeProvider CreateTreeProvider() {
    EclipseExtensionPoint extensionPoint = createExtensionPoint();
    NamePredicate treeProviderPredicate = NamePredicate.ForElementName(TAG_TREE_PROVIDER);
    ClassConveyerBelt<IExecutableTreeProvider> conveyerBelt = new ClassConveyerBelt<IExecutableTreeProvider>(
        extensionPoint,
        IExecutableTreeProvider.class,
        treeProviderPredicate);
    return new CharmTreeProvider(conveyerBelt.getAllObjects());
  }

  public static ICharmTraitMap CreateTraitMap() {
    EclipseExtensionPoint extensionPoint = createExtensionPoint();
    NamePredicate predicate = NamePredicate.ForElementName(TAG_CHARM_TRAITS);
    ClassConveyerBelt<IExecutableCharmTraitMap> conveyerBelt = new ClassConveyerBelt<IExecutableCharmTraitMap>(
        extensionPoint,
        IExecutableCharmTraitMap.class,
        predicate);
    return new CharmTraitMap(conveyerBelt.getAllObjects());
  }
}