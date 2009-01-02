package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.StaticExtensionProvider;
import net.sf.anathema.charms.data.CharmPrerequisite;

import org.junit.Before;
import org.junit.Test;

public class CharmTreeExtensionPoint_ConnectedCharmTest {

  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private static final String OTHER_TREE = "otherTree"; //$NON-NLS-1$
  private static final String ROOT_ID = "charm"; //$NON-NLS-1$
  private static final String OTHER_CHARM_ID = "otherCharm"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionWithConnectedCharms() throws Exception {
    IPluginExtension tree = createPluginExtension(
        createTreePart(TREE_ID, createCharm(OTHER_CHARM_ID, ROOT_ID)),
        createTreePart(OTHER_TREE, createCharm(OTHER_CHARM_ID, ROOT_ID), createCharm(ROOT_ID)));
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree));
  }

  @Test
  public void providesNullSourcePrerequisiteForRootCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(null, new DummyCharmId(ROOT_ID))));
  }

  @Test
  public void providesRootCharmSourcePrerequisiteForOtherCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(new DummyCharmId(ROOT_ID), new DummyCharmId(
        OTHER_CHARM_ID))));
  }

  @Test
  public void doesNotDuplicateRootCharm() throws Exception {
    assertThat(point.getTree(OTHER_TREE).length, is(2));
  }
}