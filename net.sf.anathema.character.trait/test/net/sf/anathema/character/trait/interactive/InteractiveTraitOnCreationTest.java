package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.anathema.character.experience.Experience;
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

@SuppressWarnings("nls")
public class InteractiveTraitOnCreationTest {

  private static final int MAXIMUM = 19;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;
  private ArrayList<IValidator> valueValidators;

  @Before
  public void createRules() throws Exception {
    experience = new Experience();
    experience.setExperienced(false);
    basicTrait = new BasicTrait(new Identificate("Hasä"));
    DummyTraitPreferences preferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    IInteractiveFavorization favorization = createNiceMock(IInteractiveFavorization.class);
    replay(favorization);
    valueValidators = new ArrayList<IValidator>();
    interactiveTrait = new InteractiveTrait(basicTrait, experience, favorization, valueValidators, preferences, MAXIMUM);
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
    assertThat(interactiveTrait.getMaximalValue(), is(MAXIMUM));
  }
}