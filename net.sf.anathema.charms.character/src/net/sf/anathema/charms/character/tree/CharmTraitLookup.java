package net.sf.anathema.charms.character.tree;

import net.sf.anathema.charms.tree.CharmTreeProvider;
import net.sf.anathema.charms.tree.ITreeDataMap;
import net.sf.anathema.charms.tree.ITreeLookup;
import net.sf.anathema.charms.tree.ITreeProvider;
import net.sf.anathema.charms.tree.TreeDto;
import net.sf.anathema.charms.tree.TreeLookup;

public class CharmTraitLookup implements ITraitIdLookup {

  private final ITreeLookup treeLookup;
  private final ITreeDataMap dataMap;

  public CharmTraitLookup() {
    this(CharmTreeProvider.Create());
  }

  public CharmTraitLookup(ITreeProvider treeProvider) {
    this.treeLookup = new TreeLookup(treeProvider);
    this.dataMap = treeProvider;
  }

  public String getTraitId(String charmId) {
    final String treeId = treeLookup.getTreeId(charmId);
    TreeDto treeData = dataMap.getData(treeId);
    return treeData.primaryTrait;
  }
}