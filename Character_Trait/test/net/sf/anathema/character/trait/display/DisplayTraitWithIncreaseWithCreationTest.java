package net.sf.anathema.character.trait.display;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IDisplayFavorization;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class DisplayTraitWithIncreaseWithCreationTest {

  private DummyExperience experience;
  private BasicTrait basicTrait;
  private DisplayTrait displayTrait;

  @Before
  public final void createTraitWithExperienceValue3AndCreationValue2() {
    experience = new DummyExperience();
    basicTrait = new BasicTrait(new Identificate("test")); //$NON-NLS-1$
    basicTrait.getCreationModel().setValue(2);
    basicTrait.getExperiencedModel().setValue(3);
    this.displayTrait = new DisplayTrait(
        basicTrait,
        experience,
        EasyMock.createMock(IDisplayFavorization.class),
        new DummyTraitTemplate(),
        new DummyTraitPreferences(ExperienceTraitTreatment.IncreaseWithCreation));
  }

  @Test
  public void whenCreationValueIsSetTo4ExperienceValueIncreases() throws Exception {
    experience.setExperienced(false);
    displayTrait.setValue(4);
    assertEquals(4, basicTrait.getExperiencedModel().getValue());
  }

  @Test
  public void whenCreationValueIsSetTo1ExperienceValueDoesNotDecrease() throws Exception {
    experience.setExperienced(false);
    displayTrait.setValue(1);
    assertEquals(3, basicTrait.getExperiencedModel().getValue());
  }

  @Test
  public void unsetExperienceValueRemainsUnchanged() throws Exception {
    experience.setExperienced(false);
    basicTrait.getExperiencedModel().setValue(-1);
    displayTrait.setValue(4);
    assertEquals(-1, basicTrait.getExperiencedModel().getValue());
  }
}