package net.sf.anathema.character.trait.validator.where;

public class WhereModelId implements IWhere {

  private final String expectedModelId;

  public WhereModelId(String modelId) {
    expectedModelId = modelId;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    return expectedModelId.equals(validationObject.modelId);
  }
}