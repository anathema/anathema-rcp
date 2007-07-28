package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharacterViewElementTest {

  @Test
  public void canBeDeleted() throws Exception {
    assertTrue(new CharacterViewElement(null, null, null, null).canBeDeleted());
  }

}