package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.points.configuration.IPointHandler;

public class FavoredAttributeBonusPointReducer extends AbstractExecutableExtension implements IPointHandler {
  private final static String CREDIT_ID = "net.sf.anthema.character.attributes.freebies.favored"; //$NON-NLS-1$
  private final ICreditManager creditManager;
  private final IFreebiesHandler handler;

  public FavoredAttributeBonusPointReducer() {
    this(new CreditManager());
  }

  private FavoredAttributeBonusPointReducer(ICreditManager manager) {
    this(new FavoredAttributeFreebiesHandler(ModelCache.getInstance(), manager), manager);
  }

  public FavoredAttributeBonusPointReducer(IFreebiesHandler handler, ICreditManager creditManager) {
    this.handler = handler;
    this.creditManager = creditManager;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    return -handler.getPoints(characterId, creditManager.getCredit(characterId, CREDIT_ID))
        * IAttributeConstants.FAVORED_BONUS_POINT_COST;
  }
}