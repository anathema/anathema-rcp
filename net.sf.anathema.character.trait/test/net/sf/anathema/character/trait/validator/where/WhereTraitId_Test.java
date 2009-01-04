package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class WhereTraitId_Test {

  private static final String TEST_TRAIT_ID = "test.trait.id"; //$NON-NLS-1$
  private IWhere where;

  @Before
  public void createWhereTraitId() throws Exception {
    this.where = new WhereTraitId(TEST_TRAIT_ID);
  }
  
  @Test
  public void evaluatesEqualTraitIdTrue() throws Exception {
    assertTrue(where.evaluate(null, null, null, new BasicTrait(new Identificate(TEST_TRAIT_ID))));
  }
  
  @Test
  public void evaluatesOtherTraitIdFalse() throws Exception {
    assertFalse(where.evaluate(null, null, null, new BasicTrait(new Identificate("other.test.trait.id")))); //$NON-NLS-1$
  }
}