package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.points.configuration.IPointHandler;

public class FreebiesBonusPointReducer extends AbstractExecutableExtension implements IPointHandler {

  private final ICreditManager creditManager;
  private final IAttributeGroupFreebiesHandler[] freebiesHandlers;

  public FreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FreebiesBonusPointReducer(IModelProvider modelProvider, ICreditManager creditManager) {
    this.creditManager = creditManager;
    this.freebiesHandlers = new IAttributeGroupFreebiesHandler[] {
        new PrimaryAttributeFreebies(modelProvider),
        new SecondaryAttributeFreebies(modelProvider),
        new TertiaryAttributeFreebies(modelProvider) };
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    if (characterId == null) {
      return 0;
    }
    int dotsSaved = 0;
    for (IFreebiesHandler handler : freebiesHandlers) {
      int credit = creditManager.getCredit(characterId, handler.getCreditId());
      dotsSaved -= handler.getPoints(characterId, credit);
    }
    return dotsSaved * IAttributeConstants.BONUS_POINT_COST;
  }
}