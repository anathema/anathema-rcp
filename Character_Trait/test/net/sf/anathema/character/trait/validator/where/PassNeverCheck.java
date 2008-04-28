package net.sf.anathema.character.trait.validator.where;

import net.disy.commons.core.predicate.ICheck;

public class PassNeverCheck implements ICheck {

  @Override
  public boolean isConfirmed() {
    return false;
  }
}