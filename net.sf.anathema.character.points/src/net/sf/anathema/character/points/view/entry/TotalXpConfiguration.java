package net.sf.anathema.character.points.view.entry;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.experience.TotalXpCalculator;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;

public final class TotalXpConfiguration implements IPointConfiguration {
  @Override
  public int getPoints(ICharacterId characterId) {
    ModelContainer modelContainer = new ModelContainer(ModelCache.getInstance(), characterId);
    return new TotalXpCalculator(modelContainer).calculate();
  }

  @Override
  public String getName() {
    return "Total Xp";
  }
}