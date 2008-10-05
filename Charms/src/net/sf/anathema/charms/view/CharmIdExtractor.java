package net.sf.anathema.charms.view;

import org.eclipse.zest.core.widgets.GraphNode;

public class CharmIdExtractor implements ICharmIdExtractor {

  public String getCharmId(GraphNode node) {
    return (String) node.getData();
  }
}