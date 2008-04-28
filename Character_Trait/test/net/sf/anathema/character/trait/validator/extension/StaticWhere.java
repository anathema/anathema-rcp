package net.sf.anathema.character.trait.validator.extension;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.validator.where.IWhere;

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