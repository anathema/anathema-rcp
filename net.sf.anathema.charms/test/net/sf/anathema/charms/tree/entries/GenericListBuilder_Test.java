package net.sf.anathema.charms.tree.entries;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.StaticExtensionProvider;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;

import org.junit.Before;
import org.junit.Test;

public class GenericListBuilder_Test {
  private static final String CHARACTER_TYPE = "type"; //$NON-NLS-1$
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
    IPluginExtension charms = createPluginExtension(createGenericCharms(
        CHARACTER_TYPE,
        explicitGenericRoot,
        implicitePrerequisite));
    IPluginExtension data = createPluginExtension(createDataElement());
    point = new CharmTreeExtensionPoint(new StaticExtensionProvider(charms, data));
  }

  private IExtensionElement createDataElement() throws Exception {
    return createExtensionElementWithAttributes(new MockName("tree"), //$NON-NLS-1$
        new MockStringAttribute("id", TREE_ID), //$NON-NLS-1$
        new MockStringAttribute("characterType", CHARACTER_TYPE), //$NON-NLS-1$
        new MockStringAttribute("primaryTrait", TRAIT_ID)); //$NON-NLS-1$
  }

  @Test
  public void containsExplicitRootCharm() throws Exception {
    List<String> list = point.getGenericCharms(CHARACTER_TYPE);
    assertThat(list.contains(EXPLICIT_ROOT_ID), is(true));
  }

  @Test
  public void containsChildCharm() throws Exception {
    List<String> list = point.getGenericCharms(CHARACTER_TYPE);
    assertThat(list.contains(CHILD_ID), is(true));
  }

  @Test
  public void containsImplicitRootCharm() throws Exception {
    List<String> list = point.getGenericCharms(CHARACTER_TYPE);
    assertThat(list.contains(IMPLICIT_ROOT_ID), is(true));
  }
}