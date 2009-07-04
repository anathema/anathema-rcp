package net.sf.anathema.charms.character.selection;

import static net.sf.anathema.charms.providing.CharmProvidingExtensionPoint.*;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.type.CharacterTypeFinder;

public class ChooseTreePredicateFactory implements IChooseTreePredicateFactory {

  @Override
  public IPredicate<String> create(ICharacterId characterId) {
    CharacterTypeFinder characterTypeFinder = new CharacterTypeFinder();
    ICharacterType characterType = characterTypeFinder.getCharacterType(characterId);
    return new ChooseTreePredicate(characterType, CreateTreeProvider());
  }
}