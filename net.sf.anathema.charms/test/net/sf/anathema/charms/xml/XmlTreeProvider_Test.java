package net.sf.anathema.charms.xml;

import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.xml.structure.IStructuredCharm;

import org.junit.Before;
import org.junit.Test;

public class XmlTreeProvider_Test {

  private XmlTreeProvider treeProvider;
  private DummyXmlCharmCollection charmCollection;

  @Before
  public void createTreeProvider() {
    treeProvider = new XmlTreeProvider();
    charmCollection = new DummyXmlCharmCollection();
    treeProvider.addCharmCollection(charmCollection);
  }
  
  @Test
  public void addsCharmsPrerequisitesForCharmsWithExpectedTreeId() throws Exception {
    IStructuredCharm charm = createNiceMock(IStructuredCharm.class);
    expect(charm.getTreePart()).andStubReturn("myTree"); //$NON-NLS-1$
    replay(charm);
    charmCollection.add(charm);
    CharmPrerequisite[] tree = treeProvider.getTree("myTree"); //$NON-NLS-1$
    assertThat(tree.length, is(0));
    verify(charm);
  }

  @Test
  public void addsNoCharmsPrerequisitesForCharmsWithOtherTreeId() throws Exception {
    IStructuredCharm charm = createMock(IStructuredCharm.class);
    expect(charm.getTreePart()).andStubReturn("otherTree"); //$NON-NLS-1$
    replay(charm);
    charmCollection.add(charm);
    CharmPrerequisite[] tree = treeProvider.getTree("myTree"); //$NON-NLS-1$
    assertThat(tree.length, is(0));
    verify(charm);
  }

  @Test
  public void doesNotProvideGenerics() throws Exception {
    assertThat(treeProvider.getGenericCharms("Solar"), is(empty()));
  }
  
  @Test
  public void hasNoTreesInList() throws Exception {
    assertThat(treeProvider.getTreeList(), is(empty()));
  }
}