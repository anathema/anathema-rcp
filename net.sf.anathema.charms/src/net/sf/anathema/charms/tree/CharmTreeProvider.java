package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;

public class CharmTreeProvider implements ITreeProvider {

  private final Iterable< ? extends ITreeProvider> allProviders;

  public CharmTreeProvider(Iterable< ? extends ITreeProvider> allProviders) {
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