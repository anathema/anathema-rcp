package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.StaticExtensionProvider;
import net.sf.anathema.charms.extension.tree.CharmTreeExtensionPoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class CharmTreeExtensionPoint_MultipleDefinitionTest {

  private static final String CHARM_ID = "charm"; //$NON-NLS-1$
  private static final String ROOT = "root"; //$NON-NLS-1$
  private static final String OTHER_CHARM = "otherCharm"; //$NON-NLS-1$
  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionPointWithOneCharm() throws Exception {
    IPluginExtension tree = createPluginExtension(createTreePart(TREE_ID, createCharm(ROOT)), createTreePart(
        TREE_ID,
        createCharm(CHARM_ID, OTHER_CHARM)));
    IPluginExtension treeAgain = createPluginExtension(createTreePart(TREE_ID, createCharm(ROOT)), createTreePart(
        TREE_ID,
        createCharm(OTHER_CHARM, ROOT)));
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree, treeAgain));
  }

  @Test
  public void providesRegisteredTrees() throws Exception {
    assertThat(point.getTreeList(), JUnitMatchers.hasItems(TREE_ID));
  }


  @Test
  public void providesRegisteredTreesOnly() throws Exception {
    Iterator<String> iterator = point.getTreeList().iterator();
    iterator.next();
    assertThat(iterator.hasNext(), is(false));
  }


  @Test
  public void joinsSeperateTreePartDefinitions() throws Exception {
    assertThat(point.getTree(TREE_ID).length, is(3));
  }
}