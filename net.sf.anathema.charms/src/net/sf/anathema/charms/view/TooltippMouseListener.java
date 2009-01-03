package net.sf.anathema.charms.view;

import java.text.MessageFormat;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;

public final class TooltippMouseListener implements MouseMoveListener {
  private final GraphViewer viewer;
  private final Graph graphControl;
  private final TooltippFactory tooltippFactory = new TooltippFactory();

  public TooltippMouseListener(GraphViewer viewer) {
    this.viewer = viewer;
    this.graphControl = viewer.getGraphControl();
  }

  @Override
  public void mouseMove(MouseEvent e) {
    GraphNode graphNode = getGraphNodeAt(e.x, e.y);
    if (graphNode != null) {
      updateTooltipp(graphNode);
    }
  }

  private GraphNode getGraphNodeAt(int x, int y) {
    IFigure figure = graphControl.getFigureAt(x, y);
    if (!(figure instanceof Label)) {
      return null;
    }
    String labelText = ((Label) figure).getText();
    return getNodeWithLabel(labelText);
  }

  private GraphNode getNodeWithLabel(String label) {
    for (Object node : graphControl.getNodes()) {
      GraphNode graphNode = (GraphNode) node;
      if (hasLabel(graphNode, label)) {
        return graphNode;
      }
    }
    String message = MessageFormat.format("Graph node missing for label {0}.", label);
    throw new IllegalStateException(message);
  }

  private boolean hasLabel(GraphNode graphNode, String label) {
    String nodeLabel = getLabelFor(graphNode);
    return nodeLabel.equals(label);
  }

  private String getLabelFor(GraphNode graphNode) {
    ILabelProvider labelProvider = (ILabelProvider) viewer.getLabelProvider();
    return labelProvider.getText(graphNode.getData());
  }

  private void updateTooltipp(GraphNode graphNode) {
    ICharmId charmId = (ICharmId) graphNode.getData();
    graphNode.setTooltip(tooltippFactory.createFor(charmId));
  }
}