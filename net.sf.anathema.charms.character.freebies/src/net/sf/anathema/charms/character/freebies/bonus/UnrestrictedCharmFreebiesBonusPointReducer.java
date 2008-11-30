package net.sf.anathema.charms.character.freebies.bonus;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.points.cost.CostDto;
import net.sf.anathema.charms.character.freebies.CheapCharmCount;
import net.sf.anathema.charms.character.freebies.CheapCharmFreebiesHandler;
import net.sf.anathema.charms.character.freebies.UnrestrictedCharmCount;
import net.sf.anathema.charms.character.freebies.UnrestrictedCharmHandler;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.points.CharmCostExtensionPoint;
import net.sf.anathema.charms.character.points.CheapCharmPredicate;

public class UnrestrictedCharmFreebiesBonusPointReducer extends AbstractPointHandler<ICharmModel> {
  private final ICreditManager creditManager;
  private final IModelCollection modelCollection;

  public UnrestrictedCharmFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public UnrestrictedCharmFreebiesBonusPointReducer(IModelCollection modelCollection, ICreditManager creditManager) {
    super(modelCollection, ICharmModel.MODEL_ID);
    this.modelCollection = modelCollection;
    this.creditManager = creditManager;
  }

  @Override
  public int calculatePoints(ICharmModel charms, ICharacterId characterId) {
    UnrestrictedCharmHandler unrestrictedHandler = new UnrestrictedCharmHandler();
    int credit = creditManager.getCredit(characterId, unrestrictedHandler.getCreditId());
    int cheapFreebiesCount = getCheapFreebies(characterId);
    CostDto costDto = CharmCostExtensionPoint.getCost(characterId);
    int charmCount = new UnrestrictedCharmCount(charms).count();
    IPredicate<String> cheapCharmPredicate = CheapCharmPredicate.From(modelCollection, characterId);
    int cheapCharmCount = new CheapCharmCount(charms, cheapCharmPredicate).count();
    int remainingCheapCount = cheapCharmCount - cheapFreebiesCount;
    int expensiveCount = charmCount - cheapCharmCount;
    return -new UnrestrictedCharmBonusPointsExpenditure(costDto, credit).calculate(remainingCheapCount, expensiveCount);
  }

  private int getCheapFreebies(ICharacterId characterId) {
    CheapCharmFreebiesHandler cheapHandler = new CheapCharmFreebiesHandler(modelCollection);
    int credit = creditManager.getCredit(characterId, cheapHandler.getCreditId());
    return cheapHandler.getPoints(characterId, credit);
  }
}