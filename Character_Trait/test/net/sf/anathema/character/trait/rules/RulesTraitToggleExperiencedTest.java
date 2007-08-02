package net.sf.anathema.character.trait.rules;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.model.Experience;
import net.sf.anathema.character.experience.model.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.rules.internal.RuleTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class RulesTraitToggleExperiencedTest {

  private static final int LESSER_CREATION_VALUE = 3;
  private static final int EXPERIENCED_VALUE = 5;
  private IExperience experience;
  private RuleTrait ruleTrait;
  private BasicTrait basicTrait;

  @Before
  public void createRules() throws Exception {
    this.experience = new Experience();
    this.basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    this.ruleTrait = new RuleTrait(basicTrait, experience, new DummyTraitTemplate());
    basicTrait.getCreationModel().setValue(LESSER_CREATION_VALUE);
    basicTrait.getExperiencedModel().setValue(EXPERIENCED_VALUE);
  }
  
  @Test
  public void withLesserCreationValueValuesAreToggledOnExperienceChange() throws Exception {
    assertEquals(LESSER_CREATION_VALUE, ruleTrait.getValue());
    experience.setExperienced(true);
    assertEquals(EXPERIENCED_VALUE, ruleTrait.getValue());
    experience.setExperienced(false);
    assertEquals(LESSER_CREATION_VALUE, ruleTrait.getValue());
  }

  @Test
  public void withHigherCreationValueValuesAreToggledOnExperienceChange() throws Exception {
    int higherCreationValue = EXPERIENCED_VALUE + 1;
    basicTrait.getCreationModel().setValue(higherCreationValue);
    experience.setExperienced(true);
    assertEquals(higherCreationValue, ruleTrait.getValue());
    assertEquals(higherCreationValue, basicTrait.getCreationModel().getValue());
    assertEquals(EXPERIENCED_VALUE, basicTrait.getExperiencedModel().getValue());
  }
}