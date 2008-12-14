package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.StaticExtensionProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

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
  public void providesRegisteredTrees() throws Exception {
    assertThat(point.getTreeList(), JUnitMatchers.hasItems(TREE_ID, OTHER_TREE_ID, THIRD_TREE_ID));
  }

  @Test
  public void providesNoPrerequisistesForUnknownTree() throws Exception {
    assertThat(point.getTree("unknownTree").length, is(0)); //$NON-NLS-1$
  }

  @Test
  public void providesOneCharmPrerequisite() throws Exception {
    assertThat(point.getTree(TREE_ID).length, is(1));
  }

  @Test
  public void prerequisiteHasNoSource() throws Exception {
    assertThat(point.getTree(TREE_ID)[0].getSource(), is(nullValue()));
  }

  @Test
  public void charmIsDestinationOfPrerequisiste() throws Exception {
    assertThat(point.getTree(TREE_ID)[0].getDestination(), is(CHARM_ID));
  }

  @Test
  public void looksForTreepartsInLaterExtensionElements() throws Exception {
    assertThat(point.getTree(OTHER_TREE_ID)[0].getDestination(), is(OTHER_CHARM_ID));
  }

  @Test
  public void looksForTreepartsInLaterPluginElements() throws Exception {
    assertThat(point.getTree(THIRD_TREE_ID)[0].getDestination(), is(THIRD_CHARM_ID));
  }
}