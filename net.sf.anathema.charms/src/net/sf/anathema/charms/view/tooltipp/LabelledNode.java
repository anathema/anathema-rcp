package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.zest.core.widgets.GraphNode;

public class LabelledNode {

  private final GraphNode graphNode;
  private final ILabelProvider labelProvider;

  public LabelledNode(GraphNode graphNode, ILabelProvider labelProvider) {
    this.graphNode = graphNode;
    this.labelProvider = labelProvider;
  }

  public boolean hasLabel(String label) {
    String nodeLabel = labelProvider.getText(graphNode.getData());
    return nodeLabel.equals(label);
  }

  public void updateTooltipp(ITooltippFactory tooltippFactory) {
    ICharmId charmId = (ICharmId) graphNode.getData();
    graphNode.setTooltip(tooltippFactory.createFor(charmId));
  }
}