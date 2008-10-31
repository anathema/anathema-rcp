package net.sf.anathema.charms.character.points;

import net.sf.anathema.character.core.character.ICharacterId;

public class ExperienceCosts implements IExperienceCosts {

  private final ICharacterId characterId;

  public ExperienceCosts(ICharacterId characterId) {
    this.characterId = characterId;
  }

  public int getCosts(boolean cheap) {
    return cheap ? 8 : 10;
  }
}