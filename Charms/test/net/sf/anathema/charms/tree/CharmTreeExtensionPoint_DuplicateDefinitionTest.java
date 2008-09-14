package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.StaticExtensionProvider;

import org.junit.Before;
import org.junit.Test;

public class CharmTreeExtensionPoint_DuplicateDefinitionTest {

  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private static final String ROOT_ID = "charm"; //$NON-NLS-1$
  private static final String OTHER_CHARM_ID = "otherCharm"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionPointWithOneCharm() throws Exception {
    IPluginExtension tree = createPluginExtension(createTree(
        TREE_ID,
        createCharm(OTHER_CHARM_ID, ROOT_ID),
        createCharm(OTHER_CHARM_ID, ROOT_ID)));
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree));
  }

  @Test
  public void doesNotDuplicateCharmPrerequisites() throws Exception {
    assertEquals(2, point.getTree(TREE_ID).length);
  }
}