package net.sf.anathema.charms.character.selection;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;

public interface IChooseTreePredicateFactory {

  public IPredicate<String> create(ICharacterId characterId);
}