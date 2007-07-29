package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;

import net.sf.anathema.basics.repository.treecontent.itemtype.IPageDelible;

import org.junit.Test;

public class CharacterViewElementTest {

  @Test
  public void canBeDeleted() throws Exception {
    assertTrue(new CharacterViewElement(null, null, null, null).getAdapter(IPageDelible.class) instanceof IPageDelible);
  }

}