package net.sf.anathema.character.trait.validator.where;

public class WhereTraitId implements IWhere {

  private final String expectedId;

  public WhereTraitId(String expectedId) {
    this.expectedId = expectedId;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    return expectedId.equals(validationObject.trait.getTraitType().getId());
  }
}