package net.sf.anathema.charms.character.combo;

import net.disy.commons.core.predicate.IPredicate;

public class IsComboKeyword implements IPredicate<String> {

  @Override
  public boolean evaluate(String keyword) {
    return keyword.toLowerCase().startsWith("combo");
  }
}