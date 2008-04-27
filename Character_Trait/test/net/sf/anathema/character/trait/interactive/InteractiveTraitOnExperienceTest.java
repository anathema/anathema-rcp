package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitOnExperienceTest {

  private static final int CURRENT_ESSENCE = 7;
  private static final int MAX_VALUE = 4;
  private static final int MIN_VALUE = 0;
  private static final int CREATION_VALUE = 2;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;

  @Test
  public void allowsValueGreaterThanCreationValue() throws Exception {
    int value = CREATION_VALUE + 1;
    interactiveTrait.setValue(value);
    assertEquals(value, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }

  private void assertNoCreationValueChange() {
    assertEquals(CREATION_VALUE, basicTrait.getCreationModel().getValue());
  }

  @Before
  public void createRules() throws Exception {
    DummyTraitTemplate traitTemplate = new DummyTraitTemplate();
    traitTemplate.setMinimalValue(MIN_VALUE);
    traitTemplate.setMaximalValue(MAX_VALUE);
    experience = new DummyExperience();
    experience.setExperienced(true);
    basicTrait = new BasicTrait(new Identificate("Hasä")); //$NON-NLS-1$
    basicTrait.getCreationModel().setValue(CREATION_VALUE);
    IInteractiveFavorization favorization = createNiceMock(IInteractiveFavorization.class);
    replay(favorization);
    interactiveTrait = new InteractiveTrait(
        basicTrait,
        ModelContainerObjectMother.create(experience),
        favorization,
        traitTemplate,
        null);
  }

  @Test
  public void enforcesCreationValueMinimum() throws Exception {
    interactiveTrait.setValue(CREATION_VALUE - 1);
    assertEquals(CREATION_VALUE, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }

  @Test
  public void enforcesCurrentEssenceAsMaximumValue() throws Exception {
    interactiveTrait.setValue(CURRENT_ESSENCE + 1);
    assertEquals(CURRENT_ESSENCE, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }

  @Test
  public void respectsConfiguredMaximumValue() throws Exception {
    assertEquals(MAX_VALUE, interactiveTrait.getMaximalValue());
  }
}