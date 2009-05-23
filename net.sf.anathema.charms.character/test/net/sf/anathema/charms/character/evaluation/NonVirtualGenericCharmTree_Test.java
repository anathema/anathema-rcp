package net.sf.anathema.charms.character.evaluation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import net.sf.anathema.charms.character.fake.DummyTreeProvider;
import net.sf.anathema.charms.character.fake.DummyVirtualCharms;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class NonVirtualGenericCharmTree_Test {

  private static final String OTHER_GENERIC_ID = "my other generic id";
  private static final String GENERIC_ID = "my generic id";
  private static final String CHARACTER_TYPE = "characterType";
  private NonVirtualGenericCharmTree charmTree;
  private DummyTreeProvider treeProvider;
  private DummyVirtualCharms virtualCharms;

  @Before
  public void createTreeAndAdGenericIdToProvider() throws Exception {
    treeProvider = new DummyTreeProvider();
    virtualCharms = new DummyVirtualCharms();
    this.charmTree = new NonVirtualGenericCharmTree(treeProvider, virtualCharms);
    treeProvider.genericIdsByCharactertype.add(CHARACTER_TYPE, GENERIC_ID);
  }

  @Test
  public void returnsOneNonVirtualId() throws Exception {
    Collection<String> realIds = getRealGenericIds();
    assertThat(realIds.size(), is(1));
    assertThat(realIds.iterator().next(), is(GENERIC_ID));
  }

  @Test
  public void returnsMultipleNonVirtualId() throws Exception {
    treeProvider.genericIdsByCharactertype.add(CHARACTER_TYPE, OTHER_GENERIC_ID);
    Collection<String> realIds = getRealGenericIds();
    assertThat(realIds.size(), is(2));
    Iterator<String> idIterator = realIds.iterator();
    assertThat(idIterator.next(), is(GENERIC_ID));
    assertThat(idIterator.next(), is(OTHER_GENERIC_ID));
  }

  @Test
  public void ignoresVirtualId() throws Exception {
    virtualCharms.virtualPaterns.add(GENERIC_ID);
    Collection<String> realIds = getRealGenericIds();
    assertThat(realIds.size(), is(0));
  }

  private Collection<String> getRealGenericIds() {
    return charmTree.getGenericIdPattersFor(CHARACTER_TYPE);
  }
}