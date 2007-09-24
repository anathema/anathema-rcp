package net.sf.anathema.character.core.type;

import net.sf.anathema.character.core.model.ICharacterId;

public interface ICharacterTypeFinder {

  public ICharacterType getCharacterType(ICharacterId characterId);

}