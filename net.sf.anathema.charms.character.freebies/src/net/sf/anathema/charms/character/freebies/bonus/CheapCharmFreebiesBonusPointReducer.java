package net.sf.anathema.charms.character.freebies.bonus;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.points.cost.CostDto;
import net.sf.anathema.charms.character.freebies.CheapCharmFreebiesHandler;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.points.CharmCostExtensionPoint;

public class CheapCharmFreebiesBonusPointReducer extends AbstractPointHandler<ICharmModel> {
  private final ICreditManager creditManager;
  private final IModelCollection modelCollection;

  public CheapCharmFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public CheapCharmFreebiesBonusPointReducer(IModelCollection modelCollection, ICreditManager creditManager) {
    super(modelCollection, ICharmModel.MODEL_ID);
    this.modelCollection = modelCollection;
    this.creditManager = creditManager;
  }

  @Override
  public int calculatePoints(ICharmModel charms, ICharacterId characterId) {
    CheapCharmFreebiesHandler handler = new CheapCharmFreebiesHandler(modelCollection);
    int credit = creditManager.getCredit(characterId, handler.getCreditId());
    int cheapFreebiesCount = handler.getPoints(characterId, credit);
    CostDto costDto = CharmCostExtensionPoint.getCost(characterId);
    return -new CheapCharmBonusPointsExpenditure(costDto).calculate(cheapFreebiesCount);
  }
}