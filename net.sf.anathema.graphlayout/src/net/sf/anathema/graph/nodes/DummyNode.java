package net.sf.anathema.graph.nodes;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.collection.EqualsPredicate;

public class DummyNode implements ISimpleNode {

  private final int layer;
  private final ISimpleNode[] child = new ISimpleNode[1];
  private final ISimpleNode[] parent = new ISimpleNode[1];

  public DummyNode(ISimpleNode child, int layer) {
    this.child[0] = child;
    this.layer = layer;
  }

  public ISimpleNode[] getChildren() {
    return child;
  }

  public int getLayer() {
    return layer;
  }

  public boolean isParentOf(ISimpleNode potential) {
    return ArrayUtilities.contains(child, new EqualsPredicate<ISimpleNode>(potential));
  }

  public ISimpleNode[] getParents() {
    return parent;
  }

  public void addParent(ISimpleNode node) {
    Ensure.ensureNull(parent[0]);
    parent[0] = node;
  }

  public boolean isRootNode() {
    return false;
  }

  public boolean isLeafNode() {
    return false;
  }

  public void removeParent(ISimpleNode node) {
    // Nothing to do
  }

  public boolean hasMultipleParents() {
    return false;
  }

  public ISimpleNode[] getChildren(ISimpleNode[] childrenLayer) {
    return getChildren();
  }

  public void reorderChildren(ISimpleNode[] orderedNodes) {
    // Nothing to do
  }

  @Override
  public String toString() {
    return "Dummynode (Layer:" + getLayer() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}