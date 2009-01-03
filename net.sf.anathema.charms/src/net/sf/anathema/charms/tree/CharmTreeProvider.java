package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.NamePredicate;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmPrerequisite;

public class CharmTreeProvider extends AbstractExecutableExtension implements ITreeProvider {

  private final Iterable<ITreeProvider> allProviders;

  public static ITreeProvider Create() {
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, "charmproviding");
    NamePredicate treeProviderPredicate = NamePredicate.ForElementName("treeProvider");
    ClassConveyerBelt<ITreeProvider> conveyerBelt = new ClassConveyerBelt<ITreeProvider>(
        extensionPoint,
        ITreeProvider.class,
        treeProviderPredicate);
    return new CharmTreeProvider(conveyerBelt.getAllObjects());
  }

  private CharmTreeProvider(Iterable<ITreeProvider> allProviders) {
    this.allProviders = allProviders;
  }

  @Override
  public CharmPrerequisite[] getTree(String id) {
    Set<CharmPrerequisite> allPrerequisites = new LinkedHashSet<CharmPrerequisite>();
    for (ITreeProvider provider : allProviders) {
      allPrerequisites.addAll(Arrays.asList(provider.getTree(id)));
    }
    return allPrerequisites.toArray(allPrerequisites.toArray(new CharmPrerequisite[allPrerequisites.size()]));
  }

  @Override
  public List<String> getTreeList() {
    Set<String> allIds = new LinkedHashSet<String>();
    for (ITreeProvider provider : allProviders) {
      allIds.addAll(provider.getTreeList());
    }
    return new ArrayList<String>(allIds);
  }

  @Override
  public TreeDto getData(String id) {
    for (ITreeProvider treeProvider : allProviders) {
      TreeDto data = treeProvider.getData(id);
      if (data != null) {
        return data;
      }
    }
    return null;
  }

  @Override
  public List<String> getGenericCharms(String typeId) {
    Set<String> allGenerics = new LinkedHashSet<String>();
    for (ITreeProvider provider : allProviders) {
      allGenerics.addAll(provider.getGenericCharms(typeId));
    }
    return new ArrayList<String>(allGenerics);
  }
}