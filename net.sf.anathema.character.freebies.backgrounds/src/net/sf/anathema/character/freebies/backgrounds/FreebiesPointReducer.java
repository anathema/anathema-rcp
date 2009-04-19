package net.sf.anathema.character.freebies.backgrounds;

import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.AbstractLimitedFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FreebiesPointReducer extends AbstractPointHandler<ITraitCollectionModel> {
  private final IModelCollection modelCollection;
  private final ICreditManager creditManager;

  public FreebiesPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FreebiesPointReducer(IModelCollection modelCollection, ICreditManager creditManager) {
    super(modelCollection, IBackgroundModel.MODEL_ID);
    this.modelCollection = modelCollection;
    this.creditManager = creditManager;
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel model, ICharacterId characterId) {
    int credit = creditManager.getCredit(characterId, BackgroundFreebiesConstants.CREDIT_ID);
    AbstractLimitedFreebiesHandler handler = new FreebiesHandler(modelCollection);
    return -handler.getPoints(characterId, credit) * 1;
  }
}