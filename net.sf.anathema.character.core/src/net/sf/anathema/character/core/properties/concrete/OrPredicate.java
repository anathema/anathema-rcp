package net.sf.anathema.character.core.properties.concrete;

import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacter;

public class OrPredicate implements IPredicate<ICharacter> {

  private final List<IPredicate<ICharacter>> predicates;

  public OrPredicate(List<IPredicate<ICharacter>> subPredicates) {
    this.predicates = subPredicates;
  }

  @Override
  public boolean evaluate(ICharacter character) {
    for (IPredicate<ICharacter> predicate : predicates) {
      if (predicate.evaluate(character)) {
        return true;
      }
    }
    return false;
  }
}