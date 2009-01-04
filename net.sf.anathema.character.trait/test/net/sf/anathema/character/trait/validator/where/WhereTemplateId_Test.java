package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WhereTemplateId_Test {

  private static final String TEST_TEMPLATE_ID = "test.template.id"; //$NON-NLS-1$
  private IWhere where;

  @Before
  public void createWhereTemplateId() throws Exception {
    this.where = new WhereTemplateId(TEST_TEMPLATE_ID);
  }
  
  @Test
  public void evaluatesEqualTemplateIdTrue() throws Exception {
    assertTrue(where.evaluate(TEST_TEMPLATE_ID, null, null, null));
  }
  
  @Test
  public void evaluatesOtherTemplateIdFalse() throws Exception {
    assertFalse(where.evaluate("other.test.template.id", null, null, null)); //$NON-NLS-1$
  }
}