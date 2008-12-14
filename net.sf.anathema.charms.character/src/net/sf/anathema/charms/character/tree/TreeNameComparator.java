package net.sf.anathema.charms.character.tree;

import java.util.Comparator;

import net.sf.anathema.charms.tree.TreeDto;

public final class TreeNameComparator implements Comparator<TreeDto> {
  @Override
  public int compare(TreeDto o1, TreeDto o2) {
    return o1.name.compareTo(o2.name);
  }
}