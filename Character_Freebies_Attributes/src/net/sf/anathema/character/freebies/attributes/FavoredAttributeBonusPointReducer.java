package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.points.AbstractPointHandler;
import net.sf.anathema.character.attributes.points.IAttributeConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.repository.ModelResourceHandler;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FavoredAttributeBonusPointReducer extends AbstractPointHandler {
  private static final String HANDLER_TYPE = "favoredAttributes"; //$NON-NLS-1$
  private final static String CREDIT_ID = "net.sf.anthema.character.attributes.freebies.favored"; //$NON-NLS-1$
  private final ICreditManager creditManager;
  private final IModelCollection modelCollection;

  public FavoredAttributeBonusPointReducer() {
    this(ModelCache.getInstance(), new ModelResourceHandler(), new CreditManager());
  }

  public FavoredAttributeBonusPointReducer(
      IModelCollection modelCollection,
      IModelResourceHandler resourceHandler,
      ICreditManager creditManager) {
    super(modelCollection, resourceHandler, HANDLER_TYPE);
    this.modelCollection = modelCollection;
    this.creditManager = creditManager;
  }

  protected IFreebiesHandler createFreebiesHandler() {
    return new FavoredAttributeFreebiesHandler(modelCollection, creditManager);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId) {
    IFreebiesHandler freebiesHandler = createFreebiesHandler();
    int credit = creditManager.getCredit(characterId, CREDIT_ID);
    return -freebiesHandler.getPoints(characterId, credit) * IAttributeConstants.FAVORED_BONUS_POINT_COST;
  }
}