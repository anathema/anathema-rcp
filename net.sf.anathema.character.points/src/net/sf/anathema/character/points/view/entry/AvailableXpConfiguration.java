package net.sf.anathema.character.points.view.entry;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.experience.AvailableXpCalculator;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;

public final class AvailableXpConfiguration implements IPointConfiguration {
  @Override
  public int getPoints(ICharacterId characterId) {
    ModelContainer modelContainer = new ModelContainer(ModelCache.getInstance(), characterId);
    return new AvailableXpCalculator(modelContainer).calculate();
  }

  @Override
  public String getName() {
    return "Available Xp";
  }
}