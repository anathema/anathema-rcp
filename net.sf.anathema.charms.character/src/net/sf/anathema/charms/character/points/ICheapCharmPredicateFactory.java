package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;

public interface ICheapCharmPredicateFactory {

  public IPredicate<String> create(ICharacterId id);
}