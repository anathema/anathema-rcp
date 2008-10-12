package net.sf.anathema.charms.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.graph.SugiyamaLayout;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.IdentifiedRegularNode;

import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

public class SugiyamaLayoutAlgorithm extends AbstractLayoutAlgorithm {

  private static final int LAYER_HEIGHT = 70;
  private static final int INITIAL_OFFSET = 20;
  private final ICharmIdExtractor idExtractor;

  public SugiyamaLayoutAlgorithm(ICharmIdExtractor idExtractor) {
    super(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
    this.idExtractor = idExtractor;
  }

  @Override
  protected void preLayoutAlgorithm(
      InternalNode[] entitiesToLayout,
      InternalRelationship[] relationshipsToConsider,
      double x,
      double y,
      double width,
      double height) {
    // nothing to do
  }

  @Override
  protected void applyLayoutInternal(
      InternalNode[] entitiesToLayout,
      InternalRelationship[] relationshipsToConsider,
      double boundsX,
      double boundsY,
      double boundsWidth,
      double boundsHeight) {
    List<IdentifiedRegularNode> nodes = new ArrayList<IdentifiedRegularNode>();
    Map<IdentifiedRegularNode, InternalNode> zestNodesByNode = new HashMap<IdentifiedRegularNode, InternalNode>();
    prepareNodes(entitiesToLayout, relationshipsToConsider, nodes, zestNodesByNode);
    IdentifiedRegularNode[] allNodes = nodes.toArray(new IdentifiedRegularNode[nodes.size()]);
    computePositions(zestNodesByNode, new SugiyamaLayout().createProperHierarchicalGraphs(allNodes));
  }

  private void prepareNodes(
      InternalNode[] entitiesToLayout,
      InternalRelationship[] relationshipsToConsider,
      List<IdentifiedRegularNode> nodes,
      Map<IdentifiedRegularNode, InternalNode> zestNodesByNodes) {
    Map<InternalNode, IdentifiedRegularNode> nodesByZestNode = new HashMap<InternalNode, IdentifiedRegularNode>();
    for (InternalNode entity : entitiesToLayout) {
      GraphNode graphData = (GraphNode) entity.getLayoutEntity().getGraphData();
      String charmId = idExtractor.getCharmId(graphData.getData());
      IdentifiedRegularNode regularNode = new IdentifiedRegularNode(charmId);
      nodesByZestNode.put(entity, regularNode);
      zestNodesByNodes.put(regularNode, entity);
      nodes.add(regularNode);
    }
    for (InternalRelationship relationship : relationshipsToConsider) {
      InternalNode source = relationship.getSource();
      InternalNode destination = relationship.getDestination();
      if (destination != null) {
        IdentifiedRegularNode sourceNode = nodesByZestNode.get(source);
        IdentifiedRegularNode destinationNode = nodesByZestNode.get(destination);
        sourceNode.addChild(destinationNode);
        destinationNode.addParent(sourceNode);
      }
    }
  }

  private void computePositions(
      Map<IdentifiedRegularNode, InternalNode> zestNodesByZest,
      IProperHierarchicalGraph[] graphs) {
    double x = INITIAL_OFFSET;
    for (IProperHierarchicalGraph graph : graphs) {
      double layerY = INITIAL_OFFSET;
      double maxLayerWidth = 0;
      for (int layerIndex = 1; layerIndex <= graph.getDeepestLayer(); layerIndex++) {
        double nodeX = x;
        ISimpleNode[] layerNodes = graph.getNodesByLayer(layerIndex);
        for (ISimpleNode node : layerNodes) {
          InternalNode zestNode = zestNodesByZest.get(node);
          zestNode.setInternalLocation(nodeX, layerY);
          nodeX += zestNode.getWidthInLayout() + 5;
          maxLayerWidth = Math.max(maxLayerWidth, nodeX - x);
        }
        layerY += LAYER_HEIGHT;
      }
      x += maxLayerWidth + INITIAL_OFFSET;
    }
  }

  @Override
  protected void postLayoutAlgorithm(InternalNode[] entitiesToLayout, InternalRelationship[] relationshipsToConsider) {
    updateLayoutLocations(entitiesToLayout);
    fireProgressEvent(1, 1);
  }

  @Override
  protected int getCurrentLayoutStep() {
    return 0;
  }

  @Override
  protected int getTotalNumberOfLayoutSteps() {
    return 1;
  }

  @Override
  protected boolean isValidConfiguration(boolean asynchronous, boolean continueous) {
    if (asynchronous && continueous) {
      return false;
    }
    else if (asynchronous && !continueous) {
      return true;
    }
    else if (!asynchronous && continueous) {
      return false;
    }
    else if (!asynchronous && !continueous) {
      return true;
    }

    return false;
  }

  @Override
  public void setLayoutArea(double x, double y, double width, double height) {
    throw new RuntimeException();
  }
}