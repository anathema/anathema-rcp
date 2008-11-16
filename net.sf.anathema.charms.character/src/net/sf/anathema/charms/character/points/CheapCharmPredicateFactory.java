package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;

public class CheapCharmPredicateFactory implements ICheapCharmPredicateFactory {

  private final IModelCollection modelCollection;

  public CheapCharmPredicateFactory(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }

  @Override
  public IPredicate<String> create(ICharacterId id) {
    return CheapCharmPredicate.createFrom(modelCollection, id);
  }
}