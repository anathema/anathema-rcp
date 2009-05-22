package net.sf.anathema.character.points.view.entry;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.experience.TotalXpCalculator;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;

public final class LifetimeXpConfiguration implements IPointConfiguration {

  private final IModelCollection modelCollection;

  public LifetimeXpConfiguration(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelContainer modelContainer = new ModelContainer(modelCollection, characterId);
    return new TotalXpCalculator(modelContainer).calculate();
  }

  @Override
  public String getName() {
    return "Lifetime Xp";
  }
}