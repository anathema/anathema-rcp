package net.sf.anathema.charms.view.node;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.zest.core.widgets.GraphNode;

public class LabelledNode {

  private final GraphNode graphNode;
  private final ILabelProvider labelProvider;

  public LabelledNode(GraphNode graphNode, ILabelProvider labelProvider) {
    this.graphNode = graphNode;
    this.labelProvider = labelProvider;
  }
}