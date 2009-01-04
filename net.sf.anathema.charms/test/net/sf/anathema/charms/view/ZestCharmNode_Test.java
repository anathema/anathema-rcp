package net.sf.anathema.charms.view;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.IContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZestCharmNode_Test {

  private Shell parent;
  private GraphNode graphNode;
  private Color color;
  private ZestCharmNode charmNode;

  @Before
  public void createCharmNode() throws Exception {
    parent = new Shell();
    color = new Color(parent.getDisplay(), 0, 250, 0);
    IContainer container = new Graph(parent, SWT.NONE);
    graphNode = new GraphNode(container, SWT.NONE);
    charmNode = new ZestCharmNode(graphNode);
  }

  @Test
  public void hasParentDisplay() throws Exception {
    assertThat(charmNode.getDisplay(), is(sameInstance(parent.getDisplay())));
  }

  @Test
  public void changesBackgroundColorToSetColor() throws Exception {
    charmNode.setColor(color);
    assertThat(graphNode.getBackgroundColor(), is(color));
  }

  @Test
  public void changesHighlightColorToSetColor() throws Exception {
    charmNode.setColor(color);
    assertThat(graphNode.getHighlightColor(), is(color));
  }

  @Test
  public void unhighlightsNodeOnColorChange() throws Exception {
    graphNode.highlight();
    charmNode.setColor(color);
    assertThat(ZestTestUtilities.callIsHighlighted(graphNode), is(false));
  }

  @After
  public void disposeParent() throws Exception {
    color.dispose();
    parent.dispose();
  }
}