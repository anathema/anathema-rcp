package net.sf.anathema.character.spiritualtraits.points.essence;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.type.CharacterTypeFinder;

public class CharacterExperienceCost {

  private final ICharacterId characterId;
  private final IEssenceCostMap costMap;

  public CharacterExperienceCost(final ICharacterId characterId, final IEssenceCostMap essenceCostMap) {
    this.characterId = characterId;
    this.costMap = essenceCostMap;
  }

  public int getXpCost() {
    final CharacterTypeFinder characterTypeFinder = new CharacterTypeFinder();
    final ICharacterType characterType = characterTypeFinder.getCharacterType(characterId);
    final String characterTypeId = characterType.getId();
    return costMap.getEssenceExperienceCost(characterTypeId);
  }
}