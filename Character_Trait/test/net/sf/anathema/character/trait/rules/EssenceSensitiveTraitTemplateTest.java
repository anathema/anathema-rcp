package net.sf.anathema.character.trait.rules;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EssenceSensitiveTraitTemplateTest {

  private EssenceSensitiveTraitTemplate traitTemplate;

  @Before
  public void testname() throws Exception {
    traitTemplate = new EssenceSensitiveTraitTemplate();
  }
  
  @Test
  public void returns5AsMaximumForLowEssenceValue() throws Exception {
    assertEquals(5, traitTemplate.getMaximalValue(4));
  }
  
  @Test
  public void returnsEssenceValueAsMaximumForHighEssenceValue() throws Exception {
    assertEquals(6, traitTemplate.getMaximalValue(6));
  }
}