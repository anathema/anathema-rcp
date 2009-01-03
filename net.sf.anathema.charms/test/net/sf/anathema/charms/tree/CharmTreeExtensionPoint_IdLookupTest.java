package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.StaticExtensionProvider;

import org.junit.Before;
import org.junit.Test;

public class CharmTreeExtensionPoint_IdLookupTest {
  private static final String CHARM_ID = "charm"; //$NON-NLS-1$
  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private static final String OTHER_TREE_ID = "tree2"; //$NON-NLS-1$
  private static final String OTHER_CHARM_ID = "otherCharm"; //$NON-NLS-1$
  private static final String TRAIT_ID = "trait"; //$NON-NLS-1$
  private static final String OTHER_TRAIT_ID = "trait2"; //$NON-NLS-1$
  private static final String GENERIC_CHARM_ID = "a.{0}.charm"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionPointTwoSingleCharms() throws Exception {
    IPluginExtension tree = createPluginExtension(createTreeElement(TREE_ID, CHARM_ID, OTHER_CHARM_ID));
    IPluginExtension data = createPluginExtension(createDataElement(TREE_ID, TRAIT_ID));
    IPluginExtension tree2 = createPluginExtension(createTreeElement(OTHER_TREE_ID, GENERIC_CHARM_ID));
    IPluginExtension data2 = createPluginExtension(createDataElement(OTHER_TREE_ID, OTHER_TRAIT_ID));
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree, tree2, data, data2));
  }

  private IExtensionElement createDataElement(String treeId, String traitId) throws Exception {
    return createExtensionElementWithAttributes(new MockName("tree"), //$NON-NLS-1$
        new MockStringAttribute("id", treeId), //$NON-NLS-1$
        new MockStringAttribute("primaryTrait", traitId)); //$NON-NLS-1$
  }

  @Test
  public void returnsRequestedCharmId() throws Exception {
    assertThat(point.getCharmId(CHARM_ID), is((ICharmId) new CharmId(CHARM_ID, TRAIT_ID)));
  }

  @Test
  public void returnsRequestedOtherCharmId() throws Exception {
    assertThat(point.getCharmId(OTHER_CHARM_ID), is((ICharmId) new CharmId(OTHER_CHARM_ID, TRAIT_ID)));
  }

  @Test
  public void returnsRequestedGenericCharm() throws Exception {
    assertThat(point.getCharmId("a.trait2.charm"), is((ICharmId) new CharmId(GENERIC_CHARM_ID, OTHER_TRAIT_ID))); //$NON-NLS-1$
  }

  @Test
  public void returnsRequestedGenericCharmWithPattern() throws Exception {
    assertThat(
        point.getCharmId("a.trait2.charm").getIdPattern(), //$NON-NLS-1$
        is(new CharmId(GENERIC_CHARM_ID, OTHER_TRAIT_ID).getIdPattern()));
  }
}