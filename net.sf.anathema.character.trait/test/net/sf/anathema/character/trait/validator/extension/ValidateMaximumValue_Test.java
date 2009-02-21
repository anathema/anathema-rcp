package net.sf.anathema.character.trait.validator.extension;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidateMaximumValue_Test {

  private static final int MAXIMUM = 3;
  private ValidateMaximalValue validator;

  @Before
  public void createMaximumValidator() {
    validator = new ValidateMaximalValue(MAXIMUM);
  }

  @Test
  public void allowsReducedValue() throws Exception {
    int reducedValue = MAXIMUM - 1;
    assertThat(validator.getValidValue(reducedValue), is(reducedValue));
  }

  @Test
  public void allowsMaximum() throws Exception {
    assertThat(validator.getValidValue(MAXIMUM), is(MAXIMUM));
  }

  @Test
  public void reducesExceedingValue() throws Exception {
    assertThat(validator.getValidValue(MAXIMUM + 1), is(MAXIMUM));
  }
}