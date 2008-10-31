package net.sf.anathema.charms.character.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.type.CharacterTypeFinder;

public class FavorizationProvider implements IFavorizationProvider {

  private final ICharacterId characterId;
  private final IModelCollection modelCollection;

  public FavorizationProvider(ICharacterId characterId, IModelCollection modelCollection) {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    this.characterId = characterId;
    this.modelCollection = modelCollection;
  }

  public boolean isFavored(String charmId) {
    return true;
  }
}