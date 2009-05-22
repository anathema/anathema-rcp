package net.sf.anathema.character.experience;

import static net.sf.anathema.character.experience.IExperiencePoints.*;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.points.ExperienceEntry;
import net.sf.anathema.lib.lang.ICalculator;

public class LifetimeXpCalculator implements ICalculator {

  private final IModelContainer modelContainer;

  public LifetimeXpCalculator(IModelContainer modelContainer) {
    this.modelContainer = modelContainer;
  }

  @Override
  public int calculate() {
    int availableXp = 0;
    for (ExperienceEntry entry : getExperienceEntries()) {
      availableXp += entry.points;
    }
    return availableXp;
  }

  private ExperienceEntry[] getExperienceEntries() {
    IExperiencePoints model = (IExperiencePoints) modelContainer.getModel(MODEL_ID);
    return model.getEntries();
  }
}