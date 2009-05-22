package net.sf.anathema.character.points.view.entry;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.experience.TotalXpCalculator;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;

public final class BankedXpConfiguration implements IPointConfiguration {

  private final IModelCollection modelCollection;
  private final List<IPointConfiguration> configurations;

  public BankedXpConfiguration(IModelCollection modelCollection, List<IPointConfiguration> configurations) {
    this.modelCollection = modelCollection;
    this.configurations = configurations;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelContainer modelContainer = new ModelContainer(modelCollection, characterId);
    int totalValue = new TotalXpCalculator(modelContainer).calculate();
    int spentValue = getSpentValue(characterId);
    return totalValue - spentValue;
  }

  private int getSpentValue(ICharacterId characterId) {
    int spentPoints = 0;
    for (IPointConfiguration configuration : configurations) {
      spentPoints += configuration.getPoints(characterId);
    }
    return spentPoints;
  }

  @Override
  public String getName() {
    return "Banked Xp";
  }
}