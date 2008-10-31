package net.sf.anathema.character.core.type;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.ICharacterTypeFinder;
import net.sf.anathema.character.core.character.ICharacterTypeProvider;

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