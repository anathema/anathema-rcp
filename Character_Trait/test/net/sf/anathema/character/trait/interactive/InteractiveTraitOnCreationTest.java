package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitPreferences;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitOnCreationTest {

  private static final int MAX_VALUE = 4;
  private static final int MIN_VALUE = 2;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;

  private void assertNoExperienceValueSet() {
    assertEquals(-1, basicTrait.getExperiencedModel().getValue());
  }

  @Before
  public void createRules() throws Exception {
    DummyTraitTemplate traitTemplate = new DummyTraitTemplate();
    traitTemplate.setMinimalValue(MIN_VALUE);
    traitTemplate.setMaximalValue(MAX_VALUE);
    this.experience = new DummyExperience();
    this.experience.setExperienced(false);
    this.basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    DummyTraitPreferences preferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    IInteractiveFavorization favorization = createNiceMock(IInteractiveFavorization.class);
    replay(favorization);
    this.interactiveTrait = new InteractiveTrait(basicTrait, experience, favorization, traitTemplate, preferences);
  }

  @Test
  public void enforcesMinimumValue() throws Exception {
    interactiveTrait.setValue(MIN_VALUE - 1);
    assertEquals(MIN_VALUE, basicTrait.getCreationModel().getValue());
    assertNoExperienceValueSet();
  }

  @Test
  public void enforcesMaximumValue() throws Exception {
    interactiveTrait.setValue(MAX_VALUE + 1);
    assertEquals(MAX_VALUE, basicTrait.getCreationModel().getValue());
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
  public void respectsConfiguredMaximumValue() throws Exception {
    assertEquals(MAX_VALUE, interactiveTrait.getMaximalValue());
  }
}