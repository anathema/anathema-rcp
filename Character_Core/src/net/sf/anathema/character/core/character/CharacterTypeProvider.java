package net.sf.anathema.character.core.character;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.type.ICharacterType;
import net.sf.anathema.character.core.type.ICharacterTypeFinder;

public class CharacterTypeProvider implements ICharacterTypeProvider {

  private final ICharacterId characterId;
  private final ICharacterTypeFinder finder;

  public CharacterTypeProvider(ICharacterId characterId, ICharacterTypeFinder finder) {
    this.characterId = characterId;
    this.finder = finder;
  }
  
  @Override
  public ICharacterType getCharacterType() {
    return finder.getCharacterType(characterId);
  }
}