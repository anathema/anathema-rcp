package net.sf.anathema.character.backgrounds.points;

import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.IBasicTrait;

public class BonusPointHandler extends AbstractPointHandler<IBackgroundModel> {

  private static final int EXPENSIVE_COST = 2;
  private static final int CHEAP_COST = 1;
  private static final int THRESHOLD = 3;

  public BonusPointHandler() {
    this(ModelCache.getInstance());
  }

  public BonusPointHandler(IModelCollection modelCollection) {
    super(modelCollection, IBackgroundModel.MODEL_ID);
  }

  @Override
  protected int calculatePoints(IBackgroundModel model, ICharacterId characterId) {
    int pointSum = 0;
    for (IBasicTrait trait : model.getAllTraits()) {
      int value = trait.getCreationModel().getValue();
      pointSum += getBackgroundPointCost(value);
    }
    return pointSum;
  }

  private int getBackgroundPointCost(int value) {
    int cheapDots = Math.min(value, THRESHOLD);
    int expensiveDots = Math.max(value - THRESHOLD, 0);
    return cheapDots * CHEAP_COST + expensiveDots * EXPENSIVE_COST;
  }
}