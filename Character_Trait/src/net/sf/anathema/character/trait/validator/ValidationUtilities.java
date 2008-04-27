package net.sf.anathema.character.trait.validator;

import java.util.List;

public class ValidationUtilities {

  public static int getCorrectedValue(List<IValidator> valueValidators, int value) {
    int correctedValue = value;
    for (IValidator validator : valueValidators) {
      correctedValue = validator.getValidValue(correctedValue);
    }
    return correctedValue;
  }
}