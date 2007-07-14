package net.sf.anathema.character.trait.rules;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.experience.DummyExperience;
import net.sf.anathema.character.trait.rules.internal.RuleTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class RuleTraitOnExperienceTest {

  private static final int MAX_VALUE = 4;
  private static final int MIN_VALUE = 0;
  private static final int CREATION_VALUE = 2;
  private DummyTraitTemplate traitRules;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private RuleTrait ruleTrait;

  @Test
  public void allowsValueGreaterThanCreationValue() throws Exception {
    int value = CREATION_VALUE + 1;
    ruleTrait.setValue(value);
    assertEquals(value, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }

  private void assertNoCreationValueChange() {
    assertEquals(CREATION_VALUE, basicTrait.getCreationModel().getValue());
  }
  
  @Before
  public void createRules() throws Exception {
    this.traitRules = new DummyTraitTemplate();
    this.traitRules.setMinimalValue(MIN_VALUE);
    this.traitRules.setMaximalValue(MAX_VALUE);
    this.experience = new DummyExperience(true);
    this.basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    this.basicTrait.getCreationModel().setValue(CREATION_VALUE);
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitRules);
  }
  
  @Test
  public void enforcesCreationValueMinimum() throws Exception {
    ruleTrait.setValue(CREATION_VALUE - 1);
    assertEquals(CREATION_VALUE, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }


  @Test
  public void enforcesMaximumValue() throws Exception {
    ruleTrait.setValue(MAX_VALUE + 1);
    assertEquals(MAX_VALUE, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }

  @Test
  public void respectsConfiguredMaximumValue() throws Exception {
    assertEquals(MAX_VALUE, ruleTrait.getMaximalValue());
  }
}