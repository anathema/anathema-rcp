package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitOnExperience_Test {

  public static final class TestIncreasingValidator implements IValidator {

    private final int increment;

    public TestIncreasingValidator(int increment) {
      this.increment = increment;
    }

    @Override
    public int getValidValue(int value) {
      return value + increment;
    }
  }

  private static final int MAXIMUM = 16;
  private static final int CREATION_VALUE = 2;
  private IExperience experience;
  private IBasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;
  private ArrayList<IValidator> valueValidators;

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
    experience = new Experience();
    experience.setExperienced(true);
    basicTrait = new BasicTrait(new Identificate("Has�")); //$NON-NLS-1$
    basicTrait.getCreationModel().setValue(CREATION_VALUE);
    IInteractiveFavorization favorization = createNiceMock(IInteractiveFavorization.class);
    replay(favorization);
    valueValidators = new ArrayList<IValidator>();
    interactiveTrait = new InteractiveTrait(basicTrait, experience, favorization, valueValidators, null, MAXIMUM);
  }

  @Test
  public void setValidatedValue() throws Exception {
    valueValidators.add(new TestIncreasingValidator(1));
    interactiveTrait.setValue(2);
    assertEquals(3, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }

  @Test
  public void concatenatesValidatorChanges() throws Exception {
    valueValidators.add(new TestIncreasingValidator(1));
    valueValidators.add(new TestIncreasingValidator(1));
    interactiveTrait.setValue(2);
    assertEquals(4, basicTrait.getExperiencedModel().getValue());
    assertNoCreationValueChange();
  }

  @Test
  public void hasStaticMaximumValueOf5() throws Exception {
    assertThat(interactiveTrait.getMaximalValue(), is(MAXIMUM));
  }
}