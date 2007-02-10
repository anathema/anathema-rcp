package net.sf.anathema.platform.svgtree.document.components;

import java.util.Comparator;


public final class VisualizableNodePositionComparator implements Comparator<IVisualizableNode> {
  public int compare(IVisualizableNode node1, IVisualizableNode node2) {
    return node1.getPosition() - node2.getPosition();
  }
}