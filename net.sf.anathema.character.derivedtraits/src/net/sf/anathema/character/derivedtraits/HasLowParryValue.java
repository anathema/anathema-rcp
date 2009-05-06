package net.sf.anathema.character.derivedtraits;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterType;

public class HasLowParryValue implements IPredicate<ICharacterType> {

  @Override
  public boolean evaluate(ICharacterType characterType) {
    // TODO Case 435: Ersthafe implementierung notwendig
    return !characterType.getId().equals("net.sf.anathema.character.type.mortal");
  }
}