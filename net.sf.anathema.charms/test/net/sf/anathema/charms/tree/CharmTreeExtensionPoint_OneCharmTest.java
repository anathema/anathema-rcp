package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.StaticExtensionProvider;

import org.junit.Before;
import org.junit.Test;

public class CharmTreeExtensionPoint_OneCharmTest {

  private static final String CHARM_ID = "charm"; //$NON-NLS-1$
  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private static final String OTHER_TREE_ID = "otherTree"; //$NON-NLS-1$
  private static final String OTHER_CHARM_ID = "otherCharm"; //$NON-NLS-1$
  private static final String THIRD_TREE_ID = "thirdTree"; //$NON-NLS-1$
  private static final String THIRD_CHARM_ID = "thirdCharm"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionPointWithOneCharm() throws Exception {
    IPluginExtension tree = createPluginExtension(createTreeElement(TREE_ID, CHARM_ID), createTreeElement(
        OTHER_TREE_ID,
        OTHER_CHARM_ID));
    IPluginExtension otherTree = createPluginExtension(createTreeElement(THIRD_TREE_ID, THIRD_CHARM_ID));
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree, otherTree));
  }

  @Test
  public void providesNoPrerequisistesForUnknownTree() throws Exception {
    assertEquals(0, point.getTree("unknownTree").length); //$NON-NLS-1$
  }

  @Test
  public void providesOneCharmPrerequisite() throws Exception {
    assertEquals(1, point.getTree(TREE_ID).length);
  }

  @Test
  public void prerequisiteHasNoSource() throws Exception {
    assertNull(point.getTree(TREE_ID)[0].getSource());
  }

  @Test
  public void charmIsDestinationOfPrerequisiste() throws Exception {
    assertEquals(CHARM_ID, point.getTree(TREE_ID)[0].getDestination());
  }

  @Test
  public void looksForTreepartsInLaterExtensionElements() throws Exception {
    assertEquals(OTHER_CHARM_ID, point.getTree(OTHER_TREE_ID)[0].getDestination());
  }

  @Test
  public void looksForTreepartsInLaterPluginElements() throws Exception {
    assertEquals(THIRD_CHARM_ID, point.getTree(THIRD_TREE_ID)[0].getDestination());
  }
}