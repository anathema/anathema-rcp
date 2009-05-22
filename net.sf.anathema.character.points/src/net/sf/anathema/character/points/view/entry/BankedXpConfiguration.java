package net.sf.anathema.character.points.view.entry;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.calculation.IExperienceCharacterFactory;
import net.sf.anathema.character.points.calculation.IExperienceCharacter;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;

public final class BankedXpConfiguration implements IPointConfiguration {

  private final IExperienceCharacterFactory characterFactory;

  public BankedXpConfiguration(IExperienceCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    IExperienceCharacter character = characterFactory.create(characterId);
    return character.getBankedXp();
  }

  @Override
  public String getName() {
    return "Banked Xp";
  }
}