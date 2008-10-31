package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WhereTemplateId_Test {

  private IWhere where;

  @Before
  public void createWhereTemplateId() throws Exception {
    this.where = new WhereTemplateId("test.template.id");
  }
  
  @Test
  public void evaluatesEqualTemplateIdTrue() throws Exception {
    assertTrue(where.evaluate("test.template.id", null, null, null));
  }
  
  @Test
  public void evaluatesOtherTemplateIdFalse() throws Exception {
    assertFalse(where.evaluate("other.test.template.id", null, null, null));
  }
}