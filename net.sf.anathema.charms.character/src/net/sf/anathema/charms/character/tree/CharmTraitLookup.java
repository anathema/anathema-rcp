package net.sf.anathema.charms.character.tree;

import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;
import net.sf.anathema.charms.tree.ITreeDataMap;
import net.sf.anathema.charms.tree.ITreeLookup;
import net.sf.anathema.charms.tree.TreeDto;

public class CharmTraitLookup implements ITraitIdLookup {

  private final ITreeLookup treeLookup;
  private final ITreeDataMap dataMap;

  public CharmTraitLookup() {
    this(new CharmTreeExtensionPoint(), new CharmTreeExtensionPoint());
  }

  public CharmTraitLookup(ITreeLookup treeLookup, ITreeDataMap dataMap) {
    this.treeLookup = treeLookup;
    this.dataMap = dataMap;
  }

  public String getTraitId(String charmId) {
    final String treeId = treeLookup.getTreeId(charmId);
    TreeDto treeData = dataMap.getData(treeId);
    return treeData.primaryTrait;
  }
}