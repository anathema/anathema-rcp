package net.sf.anathema.character.trait.rules;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TraitRulesTest {

  private static final int MIN_VALUE = 2;
  private TraitRules traitRules;

  @Before
  public void createRules() throws Exception {
    this.traitRules = new TraitRules();
    traitRules.setMiniumalValue(MIN_VALUE);
  }
  
  @Test
  public void enforcesMinimumValue() throws Exception {
    assertEquals(2, traitRules.getCorrectedValue(MIN_VALUE - 1));
  }
  @Test
  public void allowsValueGreaterThanMinimum() throws Exception {
    int value = MIN_VALUE + 1;
    assertEquals(value, traitRules.getCorrectedValue(value));
  }
  
  @Test
  public void maximumValueIsFive() throws Exception {
    assertEquals(5, traitRules.getMaximalValue());
  }
}