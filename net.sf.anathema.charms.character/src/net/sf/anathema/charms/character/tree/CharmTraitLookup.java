package net.sf.anathema.charms.character.tree;

import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;
import net.sf.anathema.charms.tree.ITreeLookup;

public class CharmTraitLookup implements ITraitIdLookup {

  private final ITreeLookup treeLookup;
  private final ITreeCharacterDataMap dataMap;

  public CharmTraitLookup() {
    this(new CharmTreeExtensionPoint(), new TreeCharacterExtensionPoint());
  }

  public CharmTraitLookup(ITreeLookup treeLookup, ITreeCharacterDataMap dataMap) {
    this.treeLookup = treeLookup;
    this.dataMap = dataMap;
  }

  public String getTraitId(String charmId) {
    final String treeId = treeLookup.getTreeId(charmId);
    return dataMap.getTraitId(treeId);
  }
}