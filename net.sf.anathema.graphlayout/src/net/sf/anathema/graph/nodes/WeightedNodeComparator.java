package net.sf.anathema.graph.nodes;

import java.util.Comparator;

public class WeightedNodeComparator implements Comparator<WeightedNode> {

  public int compare(WeightedNode o1, WeightedNode o2) {
    if (o1.weight == WeightedNode.NO_WEIGHT || o2.weight == WeightedNode.NO_WEIGHT) {
      return 0;
    }
    double weightDifference = o1.weight - o2.weight;
    if (weightDifference < 0) {
      return -1;
    }
    if (weightDifference > 0) {
      return 1;
    }
    return 0;
  }
}