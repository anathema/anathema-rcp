package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public class StaticWhere implements IWhere {

  private final boolean evaluation;

  public StaticWhere(boolean evaluation) {
    this.evaluation = evaluation;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    return evaluation;
  }
}