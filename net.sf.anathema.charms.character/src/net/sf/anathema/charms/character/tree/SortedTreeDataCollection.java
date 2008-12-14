package net.sf.anathema.charms.character.tree;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;
import net.sf.anathema.charms.tree.ITreeDataMap;
import net.sf.anathema.charms.tree.ITreeProvider;
import net.sf.anathema.charms.tree.TreeDto;

public class SortedTreeDataCollection implements Iterable<TreeDto> {

  private final ITreeProvider trees;
  private final ITreeDataMap dataMap;

  public static SortedTreeDataCollection From(CharmTreeExtensionPoint extensionPoint) {
    return new SortedTreeDataCollection(extensionPoint, extensionPoint);
  }

  public SortedTreeDataCollection(ITreeProvider trees, ITreeDataMap dataMap) {
    this.trees = trees;
    this.dataMap = dataMap;
  }

  @Override
  public Iterator<TreeDto> iterator() {
    List<String> treeList = trees.getTreeList();
    List<TreeDto> dataList = CollectionUtilities.transform(treeList, new DataTransformer(dataMap));
    Collections.sort(dataList, new TreeNameComparator());
    return dataList.iterator();
  }
}