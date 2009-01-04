package net.sf.anathema.charms.view;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.widgets.GraphNode;

public class ZestCharmNode implements ICharmNode {

  private final GraphNode zestNode;

  public ZestCharmNode(GraphNode zestNode) {
    this.zestNode = zestNode;
  }

  @Override
  public ICharmId getCharmId() {
    return (ICharmId) zestNode.getData();
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