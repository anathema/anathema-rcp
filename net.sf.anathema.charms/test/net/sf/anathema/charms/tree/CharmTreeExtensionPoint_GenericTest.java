package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.StaticExtensionProvider;
import net.sf.anathema.charms.data.CharmPrerequisite;

import org.junit.Before;
import org.junit.Test;

public class CharmTreeExtensionPoint_GenericTest {
  private static final String TREE_ID = "tree"; //$NON-NLS-1$
  private static final String EXPLICIT_ROOT_ID = "first.{0}"; //$NON-NLS-1$
  private static final String EXPLICIT_ROOT = "first.tree"; //$NON-NLS-1$
  private static final String IMPLICIT_ROOT_ID = "second.{0}"; //$NON-NLS-1$
  private static final String IMPLICIT_ROOT = "second.tree"; //$NON-NLS-1$
  private static final String CHILD_ID = "any.{0}"; //$NON-NLS-1$
  private CharmTreeExtensionPoint point;

  @Before
  public void createExtensionWithGenericCharms() throws Exception {
    IExtensionElement explicitGenericRoot = createGenericCharm(EXPLICIT_ROOT_ID);
    IExtensionElement implicitePrerequisite = createGenericCharm(CHILD_ID, EXPLICIT_ROOT_ID, IMPLICIT_ROOT_ID);
    IPluginExtension tree = createPluginExtension(explicitGenericRoot, implicitePrerequisite);
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(tree));
  }

  @Test
  public void containsExpliciteGenericCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(null, EXPLICIT_ROOT)));
  }

  @Test
  public void containsImplicitGenericCharm() throws Exception {
    assertThat(point.getTree(TREE_ID), contains(new CharmPrerequisite(null, IMPLICIT_ROOT)));
  }
}