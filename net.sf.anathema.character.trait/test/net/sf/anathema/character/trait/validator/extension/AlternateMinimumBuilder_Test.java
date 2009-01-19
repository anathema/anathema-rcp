package net.sf.anathema.character.trait.validator.extension;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Test;

public class AlternateMinimumBuilder_Test {

  @Test
  public void returnsNullValidatorForUnknownKey() throws Exception {
    AlternateMinimumBuilder builder = new AlternateMinimumBuilder();
    IBasicTrait trait = new BasicTrait(new Identificate("unknown")); //$NON-NLS-1$
    IValidator validator = builder.createValidator(trait);
    assertThat(validator, is(instanceOf(NullValidator.class)));
  }
}