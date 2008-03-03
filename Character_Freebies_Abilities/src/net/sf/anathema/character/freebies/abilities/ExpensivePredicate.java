package net.sf.anathema.character.freebies.abilities;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.trait.IBasicTrait;

public class ExpensivePredicate implements IPredicate<IBasicTrait> {

  @Override
  public boolean evaluate(IBasicTrait trait) {
    return !trait.getStatusManager().getStatus().isCheap();
  }
}