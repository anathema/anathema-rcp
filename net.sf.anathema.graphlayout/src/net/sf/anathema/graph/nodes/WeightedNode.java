package net.sf.anathema.graph.nodes;

public class WeightedNode {
  public static final Double NO_WEIGHT = Double.NaN;
  
  public static WeightedNode CreateFromNodeAndConnectionVector(ISimpleNode node, boolean[] connectionVector) {
    Double vectorCenter = calculateVectorCenter(connectionVector);
    return new WeightedNode(node, vectorCenter);
  }

  private static Double calculateVectorCenter(boolean[] vector) {
    int truePositionSum = 0;
    int numberOfTrues = 0;
    for (int index = 0; index < vector.length; index++) {
      if (vector[index]) {
        truePositionSum += index + 1;
        numberOfTrues++;
      }
    }
    if (numberOfTrues == 0) {
      return NO_WEIGHT;
    }
    return (double) truePositionSum / numberOfTrues;
  }

  public final ISimpleNode node;
  public final Double weight;

  public WeightedNode(ISimpleNode node, Double weight) {
    this.node = node;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return node + "(" + weight + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}