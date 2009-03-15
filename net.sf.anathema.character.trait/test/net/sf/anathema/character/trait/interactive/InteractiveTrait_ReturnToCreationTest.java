package net.sf.anathema.character.trait.interactive;

import static org.junit.Assert.*;

import java.util.ArrayList;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitPreferences;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class InteractiveTrait_ReturnToCreationTest {

  private DummyExperience experience;
  private BasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;

  @Before
  public final void createTraitWithExperienceValue3AndCreationValue2() {
    experience = new DummyExperience();
    basicTrait = new BasicTrait(new Identificate("test")); //$NON-NLS-1$
    basicTrait.getCreationModel().setValue(2);
    basicTrait.getExperiencedModel().setValue(3);
    interactiveTrait = new InteractiveTrait(
        basicTrait,
        experience,
        EasyMock.createMock(IInteractiveFavorization.class),
        new ArrayList<IValidator>(),
        new DummyTraitPreferences(ExperienceTraitTreatment.IncreaseWithCreation),
        5);
  }

  @Test
  public void whenCreationValueIsSetTo4ExperienceValueIncreases() throws Exception {
    experience.setExperienced(false);
    interactiveTrait.setValue(4);
    assertEquals(4, basicTrait.getExperiencedModel().getValue());
  }

  @Test
  public void whenCreationValueIsSetTo1ExperienceValueDoesNotDecrease() throws Exception {
    experience.setExperienced(false);
    interactiveTrait.setValue(1);
    assertEquals(3, basicTrait.getExperiencedModel().getValue());
  }

  @Test
  public void unsetExperienceValueRemainsUnchanged() throws Exception {
    experience.setExperienced(false);
    basicTrait.getExperiencedModel().setValue(-1);
    interactiveTrait.setValue(4);
    assertEquals(-1, basicTrait.getExperiencedModel().getValue());
  }

  @Test
  public void experienceChangeTriggersValueChangeEvent() throws Exception {
    IChangeListener changeListener = EasyMock.createMock(IChangeListener.class);
    changeListener.stateChanged();
    interactiveTrait.addChangeListener(changeListener);
    EasyMock.replay(changeListener);
    experience.setExperienced(true);
    EasyMock.verify(changeListener);
  }
}