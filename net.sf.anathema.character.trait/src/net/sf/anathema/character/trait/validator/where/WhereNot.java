package net.sf.anathema.character.trait.validator.where;

public class WhereNot implements IWhere {

  private final IWhere where;

  public WhereNot(IWhere where) {
    this.where = where;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    return !where.evaluate(validationObject);
  }
}