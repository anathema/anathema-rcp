package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WhereModelId_Test {

  private static final String TEST_MODEL_ID = "test.model.id"; //$NON-NLS-1$
  private IWhere where;

  @Before
  public void createWhereModelId() throws Exception {
    this.where = new WhereModelId(TEST_MODEL_ID);
  }
  
  @Test
  public void evaluatesEqualModelIdTrue() throws Exception {
    assertTrue(where.evaluate(null, null, TEST_MODEL_ID, null));
  }
  
  @Test
  public void evaluatesOtherModelIdFalse() throws Exception {
    assertFalse(where.evaluate(null, null, "other.test.model.id", null)); //$NON-NLS-1$
  }
}