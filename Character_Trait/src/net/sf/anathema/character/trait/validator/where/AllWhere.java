package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public class AllWhere implements IWhere {

  private final Iterable<IWhere> allWheres;

  public AllWhere(Iterable<IWhere> allWheres) {
    this.allWheres = allWheres;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    for (IWhere where : allWheres) {
      if (!where.evaluate(templateId, container, modelId, trait)) {
        return false;
      }
    }
    return true;
  }
}