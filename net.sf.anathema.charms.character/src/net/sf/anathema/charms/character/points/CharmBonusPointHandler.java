package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.charms.character.CharmModel;
import net.sf.anathema.charms.character.ICharmModel;

public class CharmBonusPointHandler extends AbstractExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;
  private final ICheapCharmPredicateFactory cheapPredicateFactory;

  public CharmBonusPointHandler() {
    this(ModelCache.getInstance(), new CheapCharmPredicateFactory(ModelCache.getInstance()));
  }

  public CharmBonusPointHandler(IModelCollection modelCollection, ICheapCharmPredicateFactory cheapPredicateFactory) {
    this.modelCollection = modelCollection;
    this.cheapPredicateFactory = cheapPredicateFactory;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    IPredicate<String> cheapPredicate = cheapPredicateFactory.create(characterId);
    ICharmModel charmModel = CharmModel.getFrom(modelCollection, characterId);
    return getCostsForCharms(charmModel, cheapPredicate);
  }

  private int getCostsForCharms(ICharmModel charmModel, IPredicate<String> cheapPredicate) {
    int experiencePoints = 0;
    for (String charmId : charmModel.getCreationLearnedCharms()) {
      experiencePoints += getCostForCharm(cheapPredicate, charmId);
    }
    return experiencePoints;
  }

  private int getCostForCharm(IPredicate<String> cheapPredicate, String charmId) {
    return cheapPredicate.evaluate(charmId) ? 4 : 5;
  }
}