package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.trait.model.MinimalValueFactory;

public class RespectTemplateMinimum implements IValidator {

  private final MinimalValueFactory minValueFactory;
  private final String traitId;

  public RespectTemplateMinimum(String traitId, MinimalValueFactory minValueFactory) {
    this.traitId = traitId;
    this.minValueFactory = minValueFactory;
  }

  @Override
  public int getValidValue(int value) {
    int minimalValue = minValueFactory.getMinimalValue(traitId);
    return Math.max(value, minimalValue);
  }
}