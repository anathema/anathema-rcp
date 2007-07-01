package net.sf.anathema.character.trait.rules;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class RulesTraitToggleExperiencedTest {

  private static final int CREATION_VALUE = 3;
  private static final int EXPERIENCED_VALUE = 5;
  private IExperience experience;
  private RuleTrait ruleTrait;

  @Before
  public void createRules() throws Exception {
    this.experience = new Experience();
    BasicTrait basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    this.ruleTrait = new RuleTrait(basicTrait, experience, new DummyTraitTemplate());
    basicTrait.getCreationModel().setValue(CREATION_VALUE);
    basicTrait.getExperiencedModel().setValue(EXPERIENCED_VALUE);
  }
  
  @Test
  public void foo() throws Exception {
    assertEquals(CREATION_VALUE, ruleTrait.getValue());
    experience.setExperienced(true);
    assertEquals(EXPERIENCED_VALUE, ruleTrait.getValue());
    experience.setExperienced(false);
    assertEquals(CREATION_VALUE, ruleTrait.getValue());
    experience.setExperienced(true);
    assertEquals(EXPERIENCED_VALUE, ruleTrait.getValue());
  }
}
