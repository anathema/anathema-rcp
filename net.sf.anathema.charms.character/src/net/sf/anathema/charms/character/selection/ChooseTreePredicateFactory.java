package net.sf.anathema.charms.character.selection;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.charms.character.tree.TreeCharacterExtensionPoint;

public class ChooseTreePredicateFactory implements IChooseTreePredicateFactory {

  @Override
  public IPredicate<String> create(ICharacterId characterId) {
    CharacterTypeFinder characterTypeFinder = new CharacterTypeFinder();
    ICharacterType characterType = characterTypeFinder.getCharacterType(characterId);
    return new ChooseTreePredicate(characterType, new TreeCharacterExtensionPoint());
  }
}