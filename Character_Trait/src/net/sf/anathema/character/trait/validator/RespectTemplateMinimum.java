package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.trait.model.IMinimalValueFactory;

public class RespectTemplateMinimum implements IValidator {

  private final IMinimalValueFactory minValueFactory;
  private final String traitId;

  public RespectTemplateMinimum(String traitId, IMinimalValueFactory minValueFactory) {
    this.traitId = traitId;
    this.minValueFactory = minValueFactory;
  }

  @Override
  public int getValidValue(int value) {
    int minimalValue = minValueFactory.getMinimalValue(traitId);
    return Math.max(value, minimalValue);
  }
}