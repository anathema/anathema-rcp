package net.sf.anathema.character.trait.validator.extension;

import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class StaticWhere implements IWhere {

  private final boolean evaluation;

  public StaticWhere(boolean evaluation) {
    this.evaluation = evaluation;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    return evaluation;
  }
}