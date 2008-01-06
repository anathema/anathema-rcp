package net.sf.anathema.character.caste.model;

import net.disy.commons.core.predicate.IPredicate;

public final class CasteIdPredicate implements IPredicate<ICaste> {
  private final String id;

  public CasteIdPredicate(String id) {
    this.id = id;
  }

  @Override
  public boolean evaluate(ICaste input) {
    return input.getId().equals(id);
  }
}