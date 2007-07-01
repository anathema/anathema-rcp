package net.sf.anathema.character.trait.rules;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitRules;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.experience.DummyExperience;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class RuleTraitOnCreationTest {

  private static final int MAX_VALUE = 4;
  private static final int MIN_VALUE = 2;
  private DummyTraitRules traitRules;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private RuleTrait ruleTrait;

  private void assertNoExperienceValueSet() {
    assertEquals(-1, basicTrait.getExperiencedModel().getValue());
  }

  @Before
  public void createRules() throws Exception {
    this.traitRules = new DummyTraitRules();
    traitRules.setMinimalValue(MIN_VALUE);
    this.traitRules.setMaximalValue(MAX_VALUE);
    experience = new DummyExperience(false);
    basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitRules);
  }
  
  @Test
  public void enforcesMinimumValueOnCreationValue() throws Exception {
    ruleTrait.setValue(MIN_VALUE - 1);
    assertEquals(MIN_VALUE, basicTrait.getCreationModel().getValue());
    assertNoExperienceValueSet();
  }
  
  @Test
  public void enforcesValueGreaterThanMinimumOnCreationValue() throws Exception {
    int value = MIN_VALUE + 1;
    ruleTrait.setValue(value);
    assertEquals(value, basicTrait.getCreationModel().getValue());
    assertNoExperienceValueSet();
  }
  
  @Test
  public void respectsConfiguredMaximumValue() throws Exception {
    assertEquals(MAX_VALUE, ruleTrait.getMaximalValue());
  }
}