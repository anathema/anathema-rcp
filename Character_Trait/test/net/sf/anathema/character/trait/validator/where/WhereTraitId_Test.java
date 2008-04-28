package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class WhereTraitId_Test {

  private IWhere where;

  @Before
  public void createWhereTraitId() throws Exception {
    this.where = new WhereTraitId("test.trait.id");
  }
  
  @Test
  public void evaluatesEqualTraitIdTrue() throws Exception {
    assertTrue(where.evaluate(null, null, null, new BasicTrait(new Identificate("test.trait.id"))));
  }
  
  @Test
  public void evaluatesOtherTraitIdFalse() throws Exception {
    assertFalse(where.evaluate(null, null, null, new BasicTrait(new Identificate("other.test.trait.id"))));
  }
}