package net.sf.anathema.charms.view;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.IContainer;
import org.junit.Before;
import org.junit.Test;

public class ZestCharmNode_CharmIdTest {

  private Shell parent;
  private GraphNode graphNode;

  @Before
  public void createGraphNode() throws Exception {
    parent = new Shell();
    IContainer container = new Graph(parent, SWT.NONE);
    graphNode = new GraphNode(container, SWT.NONE);
  }

  @Test
  public void extractsCharmIdFromGraphNode() throws Exception {
    ICharmIdExtractor extractor = createMock(ICharmIdExtractor.class);
    expect(extractor.getCharmId("MyNeatLittleCharm")).andReturn("BunnyMeadowGreen"); //$NON-NLS-1$ //$NON-NLS-2$
    replay(extractor);
    graphNode.setData("MyNeatLittleCharm"); //$NON-NLS-1$
    ZestCharmNode charmNode = new ZestCharmNode(graphNode, extractor);
    assertThat(charmNode.getCharmId(), is("BunnyMeadowGreen")); //$NON-NLS-1$
  }

  public void disposeParent() throws Exception {
    parent.dispose();
  }
}