package net.sf.anathema.character.trait.display;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class DisplayTraitToggleExperiencedTest {

  private static final int LESSER_CREATION_VALUE = 3;
  private static final int EXPERIENCED_VALUE = 5;
  private IExperience experience;
  private DisplayTrait displayTrait;
  private BasicTrait basicTrait;

  @Before
  public void createRules() throws Exception {
    experience = new Experience();
    basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    displayTrait = new DisplayTrait(null, basicTrait, experience, 5);
    basicTrait.getCreationModel().setValue(LESSER_CREATION_VALUE);
    basicTrait.getExperiencedModel().setValue(EXPERIENCED_VALUE);
  }

  @Test
  public void withLesserCreationValueValuesAreToggledOnExperienceChange() throws Exception {
    assertEquals(LESSER_CREATION_VALUE, displayTrait.getValue());
    experience.setExperienced(true);
    assertEquals(EXPERIENCED_VALUE, displayTrait.getValue());
    experience.setExperienced(false);
    assertEquals(LESSER_CREATION_VALUE, displayTrait.getValue());
  }

  @Test
  public void withHigherCreationValueValuesAreToggledOnExperienceChange() throws Exception {
    int higherCreationValue = EXPERIENCED_VALUE + 1;
    basicTrait.getCreationModel().setValue(higherCreationValue);
    experience.setExperienced(true);
    assertEquals(higherCreationValue, displayTrait.getValue());
    assertEquals(higherCreationValue, basicTrait.getCreationModel().getValue());
    assertEquals(EXPERIENCED_VALUE, basicTrait.getExperiencedModel().getValue());
  }
}