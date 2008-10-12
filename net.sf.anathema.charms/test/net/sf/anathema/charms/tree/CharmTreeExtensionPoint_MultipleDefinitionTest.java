package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.StaticExtensionProvider;

import org.junit.Before;
import org.junit.Test;

public class CharmTreeExtensionPoint_MultipleDefinitionTest {

  private static final String CHARM_ID = "charm"; //$NON-NLS-1$
  private static final String ROOT = "root"; //$NON-NLS-1$
  private static final String OTHER_CHARM = "otherCharm"; //$NON-NLS-1$
  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionPointWithOneCharm() throws Exception {
    IPluginExtension tree = createPluginExtension(createTree(TREE_ID, createCharm(ROOT)), createTree(
        TREE_ID,
        createCharm(CHARM_ID, OTHER_CHARM)));
    IPluginExtension treeAgain = createPluginExtension(createTree(TREE_ID, createCharm(ROOT)), createTree(
        TREE_ID,
        createCharm(OTHER_CHARM, ROOT)));
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree, treeAgain));
  }

  @Test
  public void joinsSeperateTreePartDefinitions() throws Exception {
    assertEquals(3, point.getTree(TREE_ID).length);
  }
}