package net.sf.anathema.charms.view;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.widgets.GraphNode;

public class ZestCharmNode implements ICharmNode {

  private final ICharmIdExtractor charmIdExtractor;
  private final GraphNode zestNode;

  public ZestCharmNode(GraphNode zestNode, ICharmIdExtractor charmIdExtractor) {
    this.zestNode = zestNode;
    this.charmIdExtractor = charmIdExtractor;
  }

  @Override
  public String getCharmId() {
    return charmIdExtractor.getCharmId(zestNode.getData());
  }

  @Override
  public Display getDisplay() {
    return zestNode.getDisplay();
  }

  @Override
  public void setColor(Color color) {
    zestNode.setHighlightColor(color);
    zestNode.setBackgroundColor(color);
    zestNode.unhighlight();
  }
}