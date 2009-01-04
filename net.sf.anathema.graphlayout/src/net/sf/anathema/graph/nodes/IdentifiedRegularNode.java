package net.sf.anathema.graph.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.lib.collection.ArrayUtilities;

public class IdentifiedRegularNode implements IIdentifiedRegularNode {

  private final class NodeIndexComparator implements Comparator<ISimpleNode> {
    private final ISimpleNode[] orderedNodes;

    private NodeIndexComparator(ISimpleNode[] orderedNodes) {
      this.orderedNodes = orderedNodes;
    }

    public int compare(ISimpleNode o1, ISimpleNode o2) {
      return ArrayUtilities.indexOf(orderedNodes, o1) - ArrayUtilities.indexOf(orderedNodes, o2);
    }
  }

  private final List<ISimpleNode> childList = new ArrayList<ISimpleNode>();
  private final List<ISimpleNode> parentList = new ArrayList<ISimpleNode>();
  private int layer = 0;
  private boolean lowerToChildren = false;
  private final Object id;

  public IdentifiedRegularNode(Object id, IRegularNode... children) {
    this.id = id;
    Collections.addAll(childList, children);
  }

  public void removeParent(ISimpleNode node) {
    parentList.remove(node);
  }

  public void addParent(ISimpleNode parentNode) {
    parentList.add(parentNode);
  }

  public ISimpleNode[] getParents() {
    return parentList.toArray(new ISimpleNode[parentList.size()]);
  }

  public boolean isRootNode() {
    return parentList.isEmpty();
  }

  public void setLayer(int newLayer) {
    layer = newLayer;
  }

  public ISimpleNode[] getChildren() {
    return childList.toArray(new ISimpleNode[childList.size()]);
  }

  public int getLayer() {
    return layer;
  }

  public void removeChild(ISimpleNode child) {
    childList.remove(child);
  }

  public void addChild(ISimpleNode child) {
    childList.add(child);
  }

  public void setLowerToChildren(boolean lower) {
    lowerToChildren = lower;
  }

  public boolean getLowerToChildren() {
    return lowerToChildren;
  }

  public boolean isLeafNode() {
    return childList.isEmpty();
  }

  public boolean hasMultipleParents() {
    return parentList.size() > 1;
  }

  public void reorderChildren(final ISimpleNode[] childrenLayer) {
    Collections.sort(childList, new NodeIndexComparator(childrenLayer));
  }

  public ISimpleNode[] getChildren(final ISimpleNode[] childrenLayer) {
    ISimpleNode[] unsortedChildren = getChildren();
    Arrays.sort(unsortedChildren, new NodeIndexComparator(childrenLayer));
    return unsortedChildren;
  }

  @Override
  public String toString() {
    return id + " (Layer:" + getLayer() + ")"; //$NON-NLS-1$//$NON-NLS-2$
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IdentifiedRegularNode)) {
      return false;
    }
    IdentifiedRegularNode other = (IdentifiedRegularNode) obj;
    return ObjectUtilities.equals(other.id, id);
  }

  @Override
  public int hashCode() {
    return id == null ? -1 : id.hashCode();
  }
}