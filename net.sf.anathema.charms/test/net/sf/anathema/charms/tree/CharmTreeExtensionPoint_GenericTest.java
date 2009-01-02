package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.StaticExtensionProvider;
import net.sf.anathema.charms.data.CharmPrerequisite;

import org.junit.Before;
import org.junit.Test;

public class CharmTreeExtensionPoint_GenericTest {
  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private static final String TRAIT_ID = "trait"; //$NON-NLS-1$
  private static final String EXPLICIT_ROOT_ID = "first.{0}"; //$NON-NLS-1$
  private static final String IMPLICIT_ROOT_ID = "second.{0}"; //$NON-NLS-1$
  private static final String CHILD_ID = "any.{0}"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionWithGenericCharms() throws Exception {
    IExtensionElement explicitGenericRoot = createCharm(EXPLICIT_ROOT_ID);
    IExtensionElement implicitePrerequisite = createCharm(CHILD_ID, EXPLICIT_ROOT_ID, IMPLICIT_ROOT_ID);
    IPluginExtension tree = createPluginExtension(createGenericCharms(explicitGenericRoot, implicitePrerequisite));
    IPluginExtension data = createPluginExtension(createDataElement());
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree, data));
  }

  private IExtensionElement createDataElement() throws Exception {
    return createExtensionElementWithAttributes(
        new MockName("tree"), //$NON-NLS-1$
        new MockStringAttribute("id", TREE_ID), //$NON-NLS-1$
        new MockStringAttribute("primaryTrait", TRAIT_ID)); //$NON-NLS-1$
  }

  @Test
  public void containsExplicitGenericCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(null, new CharmId(EXPLICIT_ROOT_ID, TRAIT_ID))));
  }

  @Test
  public void containsImplicitGenericCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(null, new CharmId(IMPLICIT_ROOT_ID, TRAIT_ID))));
  }
}