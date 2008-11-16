package net.sf.anathema.charms.character.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;

public class CharmCostFactory implements ICharmCostFactory {

  private final IModelCollection modelCollection;

  public CharmCostFactory(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }

  @Override
  public ICharmCost create(ICharacterId id) {
    return CharmCosts.From(id, modelCollection);
  }
}