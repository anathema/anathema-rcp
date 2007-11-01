package net.sf.anathema.character.trait.interactive;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.InteractiveTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitOnExperienceTest {

  private static final int MAX_VALUE = 4;
  private static final int MIN_VALUE = 0;
  private static final int CREATION_VALUE = 2;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private InteractiveTrait ruleTrait;

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
    DummyTraitTemplate traitTemplate = new DummyTraitTemplate();
    traitTemplate.setMinimalValue(MIN_VALUE);
    traitTemplate.setMaximalValue(MAX_VALUE);
    this.experience = new DummyExperience();
    this.experience.setExperienced(true);
    this.basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    this.basicTrait.getCreationModel().setValue(CREATION_VALUE);
    this.ruleTrait = new InteractiveTrait(basicTrait, experience, null, traitTemplate, null);
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