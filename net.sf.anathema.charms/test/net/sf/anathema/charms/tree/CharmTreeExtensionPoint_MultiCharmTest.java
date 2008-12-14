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

public class CharmTreeExtensionPoint_MultiCharmTest {

  private static final String CHARM_ID = "charm"; //$NON-NLS-1$
  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private static final String OTHER_CHARM_ID = "otherCharm"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionPointTwoSingleCharms() throws Exception {
    IPluginExtension tree = createPluginExtension(createTreeElement(TREE_ID, CHARM_ID, OTHER_CHARM_ID));
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree));
  }

  @Test
  public void providesTwoPrerequisites() throws Exception {
    assertThat(point.getTree(TREE_ID).length, is(2));
  }

  @Test
  public void providesNullSourcePrerequisiteForCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(null, CHARM_ID)));
  }

  @Test
  public void providesNullSourcePrerequisiteForOtherCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(null, OTHER_CHARM_ID)));
  }
}