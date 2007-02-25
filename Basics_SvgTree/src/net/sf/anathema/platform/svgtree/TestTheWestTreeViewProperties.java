package net.sf.anathema.platform.svgtree;

import java.awt.Cursor;
import java.awt.Dimension;

import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

public class TestTheWestTreeViewProperties implements ISvgTreeViewProperties {

  private Dimension size = new Dimension(178, 80);

  public Cursor getAddCursor() {
    return Cursor.getDefaultCursor();
  }

  public Cursor getDefaultCursor() {
    return Cursor.getDefaultCursor();
  }

  public Dimension getDimension() {
    return size;
  }

  public String getNodeName(String nodeId) {
    return nodeId;
  }

  public Cursor getRemoveCursor() {
    return Cursor.getDefaultCursor();
  }

  public String getToolTip(String nodeId) {
    return nodeId;
  }

  public boolean isNodeDeselectable(String nodeId) {
    return true;
  }

  public boolean isNodeSelectable(String nodeId) {
    return true;
  }

  public boolean isNodeSelected(String nodeId) {
    return true;
  }

  public boolean isRootNode(String nodeId) {
    return true;
  }

  public void setDimension(Dimension dimension) {
    this.size = dimension;
  }
}