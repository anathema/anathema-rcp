package net.sf.anathema.character.trait.validator;

public class RespectTemplateMinimum implements IValidator {

  private final int minimalValue;

  public RespectTemplateMinimum(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public int getValidValue(int value) {
    return Math.max(value, minimalValue);
  }
}