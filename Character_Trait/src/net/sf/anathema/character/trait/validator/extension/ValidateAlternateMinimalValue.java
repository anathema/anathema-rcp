package net.sf.anathema.character.trait.validator.extension;

import java.util.Arrays;

import net.disy.commons.core.predicate.ICheck;
import net.sf.anathema.character.trait.validator.IValidator;

public class ValidateAlternateMinimalValue implements IValidator {

  private final int minimalValue;
  private final Iterable<? extends ICheck> alternatives;

  public ValidateAlternateMinimalValue(int minimalValue, ICheck... alternatives) {
    this(minimalValue, Arrays.asList(alternatives));
  }

  public ValidateAlternateMinimalValue(int minimalValue, Iterable<? extends ICheck> alternatives) {
    this.minimalValue = minimalValue;
    this.alternatives = alternatives;
  }

  @Override
  public int getValidValue(int value) {
    for (ICheck alternative : alternatives) {
      if (alternative.isConfirmed()) {
        return value;
      }
    }
    return Math.max(minimalValue, value);
  }
}