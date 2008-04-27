package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitPreferences;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitOnCreationTest {

  private static final int CREATION_MAX = 5;
  private static final int MIN_VALUE = 2;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;

  private void assertNoExperienceValueSet() {
    assertEquals(-1, basicTrait.getExperiencedModel().getValue());
  }

  @Before
  public void createRules() throws Exception {
    experience = new DummyExperience();
    experience.setExperienced(false);
    basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    DummyTraitPreferences preferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    IInteractiveFavorization favorization = createNiceMock(IInteractiveFavorization.class);
    replay(favorization);
    interactiveTrait = new InteractiveTrait(
        basicTrait,
        ModelContainerObjectMother.create(experience),
        favorization,
        0,
        preferences);
  }

  @Test
  public void enforcesMinimumValue() throws Exception {
    interactiveTrait.setValue(MIN_VALUE - 1);
    assertEquals(MIN_VALUE, basicTrait.getCreationModel().getValue());
    assertNoExperienceValueSet();
  }

  @Test
  public void enforcesCreationMaximumValue() throws Exception {
    interactiveTrait.setValue(CREATION_MAX + 1);
    assertEquals(CREATION_MAX, basicTrait.getCreationModel().getValue());
    assertNoExperienceValueSet();
  }

  @Test
  public void allowsValueGreaterThanMinimum() throws Exception {
    int value = MIN_VALUE + 1;
    interactiveTrait.setValue(value);
    assertEquals(value, basicTrait.getCreationModel().getValue());
    assertNoExperienceValueSet();
  }

  @Test
  public void hasStaticMaximumVAlueOf5() throws Exception {
    assertEquals(5, interactiveTrait.getMaximalValue());
  }
}