package net.sf.anathema.charms.character.points;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;

public class ExperienceHandler extends UnconfiguredExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;

  public ExperienceHandler() {
    this(ModelCache.getInstance());
  }

  public ExperienceHandler(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    return new CharmExperienceExpenditure(characterId, modelCollection).getPoints();
  }
}