package net.sf.anathema.charms.view.tooltipp;

import java.text.MessageFormat;

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
  private final ITooltippFactory tooltippFactory = new TooltippFactory();

  public TooltippMouseListener(GraphViewer viewer) {
    this.viewer = viewer;
    this.graphControl = viewer.getGraphControl();
  }

  @Override
  public void mouseMove(MouseEvent e) {
    LabelledNode graphNode = getGraphNodeAt(e.x, e.y);
    if (graphNode != null) {
      graphNode.updateTooltipp(tooltippFactory);
    }
  }

  private LabelledNode getGraphNodeAt(int x, int y) {
    IFigure figure = graphControl.getFigureAt(x, y);
    if (!(figure instanceof Label)) {
      return null;
    }
    String labelText = ((Label) figure).getText();
    return getNodeWithLabel(labelText);
  }

  private LabelledNode getNodeWithLabel(String label) {
    ILabelProvider labelProvider = (ILabelProvider) viewer.getLabelProvider();
    for (Object node : graphControl.getNodes()) {
      LabelledNode labelledNode = new LabelledNode((GraphNode) node, labelProvider);
      if (labelledNode.hasLabel(label)) {
        return labelledNode;
      }
    }
    String message = MessageFormat.format("Graph node missing for label {0}.", label);
    throw new IllegalStateException(message);
  }
}