package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.attributes.points.IAttributeConstants;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FreebiesBonusPointReducer extends AbstractExecutableExtension implements IPointHandler {

  private final ICreditManager creditManager;
  private final IAttributeGroupFreebies[] freebiesHandlers;
  private final IModelProvider modelProvider;

  public FreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FreebiesBonusPointReducer(IModelProvider modelProvider, ICreditManager creditManager) {
    this.modelProvider = modelProvider;
    this.creditManager = creditManager;
    this.freebiesHandlers = new IAttributeGroupFreebies[] {
        new PrimaryAttributeFreebies(modelProvider),
        new SecondaryAttributeFreebies(modelProvider),
        new TertiaryAttributeFreebies(modelProvider) };
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    if (characterId == null) {
      return 0;
    }
    int favoredSpent = 0;
    int unfavoredSpent = 0;
    for (IAttributeGroupFreebies handler : freebiesHandlers) {
      ITraitCollectionModel model = (ITraitCollectionModel) modelProvider.getModel(new ModelIdentifier(
          characterId,
          Attributes.MODEL_ID));
      Dots dots = new AttributePointCalculator(model, new AttributeTemplate().getGroups()).dotsFor(handler.getPriority());
      int credit = creditManager.getCredit(characterId, handler.getCreditId());
      favoredSpent += dots.favoredSpentAsPartOfCredit(credit);
      unfavoredSpent += dots.unfavoredSpentAsPartOfCredit(credit);
    }
    int bonusSaved = favoredSpent * IAttributeConstants.FAVORED_BONUS_POINT_COST;
    bonusSaved += unfavoredSpent * IAttributeConstants.BONUS_POINT_COST;
    return -bonusSaved;
  }
}