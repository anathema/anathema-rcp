package net.sf.anathema.character.trait.validator.where;

public class WhereTemplateId implements IWhere {

  private final String expectedTemplateId;

  public WhereTemplateId(String expectedTemplateId) {
    this.expectedTemplateId = expectedTemplateId;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    return expectedTemplateId.equals(validationObject.templateId);
  }
}