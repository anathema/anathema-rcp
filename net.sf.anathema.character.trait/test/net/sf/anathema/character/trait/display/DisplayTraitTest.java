package net.sf.anathema.character.trait.display;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.fake.DummyFavorizationHandler;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class DisplayTraitTest {

  private IBasicTrait basicTrait;
  private DisplayTrait displayTrait;
  private IExperience experience;

  @Before
  public void createTraitWithoutSetExperienceValue() throws Exception {
    basicTrait = new BasicTrait(new Identificate("TestTrait")); //$NON-NLS-1$
    experience = new Experience();
    IDisplayFavorization favorization = new DisplayFavorization(new DummyFavorizationHandler(), basicTrait);
    displayTrait = new DisplayTrait(favorization, basicTrait, experience, 5);
  }

  @Test
  public void valueWhileOnCreationIsCreationValue() throws Exception {
    experience.setExperienced(false);
    basicTrait.getCreationModel().setValue(3);
    assertEquals(3, displayTrait.getValue());
  }

  @Test
  public void valueWhileOnExperienceIsHigherOfExperienceAndCreationValue() throws Exception {
    experience.setExperienced(true);
    basicTrait.getCreationModel().setValue(2);
    basicTrait.getExperiencedModel().setValue(3);
    assertEquals(3, displayTrait.getValue());
    basicTrait.getCreationModel().setValue(4);
    assertEquals(4, displayTrait.getValue());
  }

  @Test
  public void traitTypeIsSameAsOfBasicTrait() throws Exception {
    assertSame(basicTrait.getTraitType(), displayTrait.getTraitType());
  }
}