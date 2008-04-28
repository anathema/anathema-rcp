package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WhereModelId_Test {

  private IWhere where;

  @Before
  public void createWhereModelId() throws Exception {
    this.where = new WhereModelId("test.model.id");
  }
  
  @Test
  public void evaluatesEqualModelIdTrue() throws Exception {
    assertTrue(where.evaluate(null, null, "test.model.id", null));
  }
  
  @Test
  public void evaluatesOtherModelIdFalse() throws Exception {
    assertFalse(where.evaluate(null, null, "other.test.model.id", null));
  }
}