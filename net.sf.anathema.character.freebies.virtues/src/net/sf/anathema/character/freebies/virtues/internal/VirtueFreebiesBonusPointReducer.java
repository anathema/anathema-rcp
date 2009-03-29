package net.sf.anathema.character.freebies.virtues.internal;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.virtues.VirtueFreebiesConstants;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class VirtueFreebiesBonusPointReducer extends AbstractPointHandler<ITraitCollectionModel> {

  private final IModelCollection modelCollection;
  private final ICreditManager creditManager;
  public VirtueFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public VirtueFreebiesBonusPointReducer(IModelCollection modelCollection, ICreditManager creditManager) {
    super(modelCollection, IPluginConstants.MODEL_ID);
    this.modelCollection = modelCollection;
    this.creditManager = creditManager;
  }
  @Override
  protected int calculatePoints(ITraitCollectionModel model, ICharacterId characterId) {
    int credit = creditManager.getCredit(characterId, VirtueFreebiesConstants.FREEBIE_CREDIT);
    VirtueFreebiesHandler favoredFreebiesHandler = new VirtueFreebiesHandler(modelCollection);
    return -favoredFreebiesHandler.getPoints(characterId, credit)*3;
  }

}
