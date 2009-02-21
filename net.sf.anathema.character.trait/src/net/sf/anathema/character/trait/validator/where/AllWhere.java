package net.sf.anathema.character.trait.validator.where;

public class AllWhere implements IWhere {

  private final Iterable<IWhere> allWheres;

  public AllWhere(Iterable<IWhere> allWheres) {
    this.allWheres = allWheres;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    for (IWhere where : allWheres) {
      if (!where.evaluate(validationObject)) {
        return false;
      }
    }
    return true;
  }
}