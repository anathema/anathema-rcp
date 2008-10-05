package net.sf.anathema.charms.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.charms.graph.SugiyamaLayout;
import net.sf.anathema.charms.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.charms.graph.nodes.ISimpleNode;
import net.sf.anathema.charms.graph.nodes.IdentifiedRegularNode;

import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

public class CharmLayoutAlgorithm extends AbstractLayoutAlgorithm {

  public CharmLayoutAlgorithm() {
    super(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
  }

  @Override
  protected void preLayoutAlgorithm(
      InternalNode[] entitiesToLayout,
      InternalRelationship[] relationshipsToConsider,
      double x,
      double y,
      double width,
      double height) {
    // hier doll initialisieren
    // TODO Auto-generated method stub
  }

  @Override
  protected void applyLayoutInternal(
      InternalNode[] entitiesToLayout,
      InternalRelationship[] relationshipsToConsider,
      double boundsX,
      double boundsY,
      double boundsWidth,
      double boundsHeight) {
    Map<InternalNode, IdentifiedRegularNode> nodesByZestNode = new HashMap<InternalNode, IdentifiedRegularNode>();
    Map<IdentifiedRegularNode, InternalNode> zestNodesByZest = new HashMap<IdentifiedRegularNode, InternalNode>();
    List<IdentifiedRegularNode> nodes = new ArrayList<IdentifiedRegularNode>();
    for (InternalNode entity : entitiesToLayout) {
      GraphNode graphNode = (GraphNode) entity.getLayoutEntity().getGraphData();
      String charmId = (String) graphNode.getData();
      IdentifiedRegularNode regularNode = new IdentifiedRegularNode(charmId);
      nodesByZestNode.put(entity, regularNode);
      zestNodesByZest.put(regularNode, entity);
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
    IProperHierarchicalGraph[] graphs = new SugiyamaLayout().createProperHierarchicalGraphs(nodes.toArray(new IdentifiedRegularNode[nodes.size()]));
    double x = 20;
    for (IProperHierarchicalGraph graph : graphs) {
      double layerY = 20;
      for (int layerIndex = 1; layerIndex <= graph.getDeepestLayer(); layerIndex++) {
        double nodeX = x;
        ISimpleNode[] layerNodes = graph.getNodesByLayer(layerIndex);
        for (ISimpleNode node : layerNodes) {
          InternalNode zestNode = zestNodesByZest.get(node);
          zestNode.setInternalLocation(nodeX, layerY);
          nodeX += 30;
        }
        layerY += 70;
      }
      x += 250;
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