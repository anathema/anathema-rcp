package net.sf.anathema.charms.tree.operations;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.junit.Before;
import org.junit.Test;

public class CollectTreeIds_Test {

  private Set<String> idSet;
  private CollectTreeIds collect;

  @Before
  public void createCollect() {
    idSet = new HashSet<String>();
    collect = new CollectTreeIds(idSet);
  }

  @Test
  public void addsNoUnknownElement() throws Exception {
    collect.execute(createExtensionElement(new MockName("unknown"))); //$NON-NLS-1$
    assertThat(idSet.size(), is(0));
  }

  @Test
  public void addsIdFromTreeElement() throws Exception {
    collectIdForElementWithNameAndId("tree", "id", "my.treeid"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    assertThat(idSet.size(), is(1));
    assertThat(idSet.iterator().next(), is("my.treeid")); //$NON-NLS-1$
  }

  @Test
  public void addsIdFromTreePartElement() throws Exception {
    collectIdForElementWithNameAndId("treepart", "treeReference", "other.treeid"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    assertThat(idSet.size(), is(1));
    assertThat(idSet.iterator().next(), is("other.treeid")); //$NON-NLS-1$
  }

  private void collectIdForElementWithNameAndId(String elementName, String attributeName, String id)
      throws ExtensionException {
    MockStringAttribute idAttribute = new MockStringAttribute(attributeName, id);
    collect.execute(createExtensionElement(new MockName(elementName), idAttribute));
  }
}