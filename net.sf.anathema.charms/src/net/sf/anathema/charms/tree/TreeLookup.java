package net.sf.anathema.charms.tree;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.operations.ContainsCharm;

public class TreeLookup implements ITreeLookup {

  private final ITreeProvider treeProvider;

  public TreeLookup(ITreeProvider treeProvider) {
    this.treeProvider = treeProvider;
  }

  @Override
  public String getTreeId(String charmId) {
    for (String treeId : treeProvider.getTreeList()) {
      CharmPrerequisite[] tree = treeProvider.getTree(treeId);
      if (new ContainsCharm(tree, charmId).isConfirmed()) {
        return treeId;
      }
    }
    return null;
  }
}