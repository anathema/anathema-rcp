package net.sf.anathema.basics.repository.treecontent.itemtype;

import static org.junit.Assert.assertNull;

import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.junit.Test;

public class ItemTypeViewElementTest {

  @Test
  public void doesNotAdaptToDelible() {
    assertNull(new ItemTypeViewElement(null).getAdapter(IPageDelible.class));
  }
}
