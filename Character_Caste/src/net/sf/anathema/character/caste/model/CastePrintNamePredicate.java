package net.sf.anathema.character.caste.model;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.caste.ICaste;

public final class CastePrintNamePredicate implements IPredicate<ICaste> {
  private final String printName;

  public CastePrintNamePredicate(String printName) {
    this.printName = printName;
  }

  @Override
  public boolean evaluate(ICaste input) {
    return input.getPrintName().equals(printName);
  }
}