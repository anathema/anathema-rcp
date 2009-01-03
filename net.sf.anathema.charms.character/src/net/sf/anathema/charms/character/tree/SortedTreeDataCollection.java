package net.sf.anathema.charms.character.tree;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.charms.tree.ITreeProvider;
import net.sf.anathema.charms.tree.TreeDto;

public class SortedTreeDataCollection implements Iterable<TreeDto> {

  private final ITreeProvider trees;

  public static SortedTreeDataCollection From(ITreeProvider treeProvider) {
    return new SortedTreeDataCollection(treeProvider);
  }

  public SortedTreeDataCollection(ITreeProvider trees) {
    this.trees = trees;
  }

  @Override
  public Iterator<TreeDto> iterator() {
    List<String> treeList = trees.getTreeList();
    List<TreeDto> dataList = CollectionUtilities.transform(treeList, new DataTransformer(trees));
    Collections.sort(dataList, new TreeNameComparator());
    return dataList.iterator();
  }
}