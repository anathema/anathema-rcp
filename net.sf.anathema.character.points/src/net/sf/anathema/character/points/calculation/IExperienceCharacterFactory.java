package net.sf.anathema.character.points.calculation;

import net.sf.anathema.character.core.character.ICharacterId;

public interface IExperienceCharacterFactory {

  public IExperienceCharacter create(ICharacterId characterId);
}