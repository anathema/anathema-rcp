package net.sf.anathema.character.trait.validator.extension;

import static org.junit.Assert.assertEquals;
import net.disy.commons.core.predicate.PassAlwaysCheck;
import net.sf.anathema.character.trait.validator.extension.ValidateAlternateMinimalValue;

import org.junit.Test;

public class ValidateAlternateMinimalValue_Test {

  @Test
  public void requiresMinmimalValueIfNoAlternativeIsFullfilled() throws Exception {
    ValidateAlternateMinimalValue validator = new ValidateAlternateMinimalValue(
        2,
        new PassNeverCheck(),
        new PassNeverCheck());
    assertEquals(2, validator.getValidValue(1));
  }

  @Test
  public void allowsLowValuesIfOneAlternativeIsFullfilled() throws Exception {
    ValidateAlternateMinimalValue validator = new ValidateAlternateMinimalValue(
        2,
        new PassAlwaysCheck(),
        new PassNeverCheck());
    assertEquals(1, validator.getValidValue(1));
  }
}
