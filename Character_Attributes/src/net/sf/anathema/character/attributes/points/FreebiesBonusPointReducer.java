package net.sf.anathema.character.attributes.points;

import org.eclipse.core.resources.mapping.ModelProvider;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FreebiesBonusPointReducer extends AbstractExecutableExtension implements IPointHandler {

  private final ICreditManager creditManager;
  private final IAttributeGroupFreebiesHandler[] freebiesHandlers;
  private final IModelProvider modelProvider;

  public FreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FreebiesBonusPointReducer(IModelProvider modelProvider, ICreditManager creditManager) {
    this.modelProvider = modelProvider;
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
    int savedBonusPoints = 0;
    for (IAttributeGroupFreebiesHandler handler : freebiesHandlers) {
      ITraitCollectionModel model = (ITraitCollectionModel) modelProvider.getModel(new ModelIdentifier(
          characterId,
          Attributes.MODEL_ID));
      Dots dots = new AttributePointCalculator(model, new AttributeTemplate().getGroups()).dotsFor(handler.getPriority());
      int credit = creditManager.getCredit(characterId, handler.getCreditId());
      int favoredSpent = dots.favoredSpentAsPartOfCredit(credit);
      int unfavoredSpent = dots.unfavoredSpentAsPartOfCredit(credit);
      savedBonusPoints -= favoredSpent * IAttributeConstants.FAVORED_BONUS_POINT_COST;
      savedBonusPoints -= unfavoredSpent * IAttributeConstants.BONUS_POINT_COST;
    }
    return savedBonusPoints;
  }
}