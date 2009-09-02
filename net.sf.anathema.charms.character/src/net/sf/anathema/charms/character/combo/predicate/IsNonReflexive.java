package net.sf.anathema.charms.character.combo.predicate;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.charms.character.combo.ComboCharm;

public class IsNonReflexive implements IPredicate<ComboCharm> {

  @Override
  public boolean evaluate(ComboCharm charm) {
    return !charm.isReflexive();
  }
}
