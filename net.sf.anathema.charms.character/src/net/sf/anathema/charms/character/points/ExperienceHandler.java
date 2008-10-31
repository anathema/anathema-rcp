package net.sf.anathema.charms.character.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.charms.character.ICharmModel;

public class ExperienceHandler extends AbstractExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;

  public ExperienceHandler() {
    this(ModelCache.getInstance());
  }

  public ExperienceHandler(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelIdentifier charmIdentifier = new ModelIdentifier(characterId, ICharmModel.MODEL_ID);
    ICharmModel charmModel = (ICharmModel) modelCollection.getModel(charmIdentifier);
    int experiencePoints = 0;
    IExperienceCosts costs = new ExperienceCosts(characterId);
    IFavorizationProvider favorizationProvider = new FavorizationProvider(characterId, modelCollection);
    for (String charmId : charmModel.getExperienceLearnedCharms()) {
      if (!charmModel.isCreationLearned(charmId)) {
        experiencePoints += costs.getCosts(favorizationProvider.isFavored(charmId));
      }
    }
    return experiencePoints;
  }
}