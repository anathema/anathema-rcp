package net.sf.anathema.charms.character.combo.predicate;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.charms.character.combo.ComboCharm;

public class IsExtraAction implements IPredicate<ComboCharm> {

  @Override
  public boolean evaluate(ComboCharm charm) {
    return charm.isExtraAction();
  }
}
