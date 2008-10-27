package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitPreferences;
import net.sf.anathema.character.trait.interactive.InteractiveTraitOnExperience_Test.TestIncreasingValidator;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitOnCreationTest {

  private IExperience experience;
  private IBasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;
  private ArrayList<IValidator> valueValidators;

  @Before
  public void createRules() throws Exception {
    experience = new DummyExperience();
    experience.setExperienced(false);
    basicTrait = new BasicTrait(new Identificate("Has�")); //$NON-NLS-1$
    DummyTraitPreferences preferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    IInteractiveFavorization favorization = createNiceMock(IInteractiveFavorization.class);
    replay(favorization);
    valueValidators = new ArrayList<IValidator>();
    interactiveTrait = new InteractiveTrait(
        basicTrait,
        ModelContainerObjectMother.create(experience),
        favorization,
        valueValidators,
        preferences);
  }

  @Test
  public void setValidatedValue() throws Exception {
    valueValidators.add(new TestIncreasingValidator(1));
    interactiveTrait.setValue(2);
    assertEquals(3, basicTrait.getCreationModel().getValue());
  }

  @Test
  public void concatenatesValidatorChanges() throws Exception {
    valueValidators.add(new TestIncreasingValidator(1));
    valueValidators.add(new TestIncreasingValidator(1));
    interactiveTrait.setValue(2);
    assertEquals(4, basicTrait.getCreationModel().getValue());
  }

  @Test
  public void hasStaticMaximumValueOf5() throws Exception {
    assertEquals(5, interactiveTrait.getMaximalValue());
  }
}