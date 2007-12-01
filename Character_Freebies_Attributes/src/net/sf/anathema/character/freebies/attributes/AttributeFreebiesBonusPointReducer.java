package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeGroupConfiguration;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.attributes.points.IAttributeConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeFreebiesBonusPointReducer extends AbstractExecutableExtension implements IPointHandler {

  private final ICreditManager creditManager;
  private final IAttributeGroupFreebies[] freebiesHandlers;
  private final IModelCollection modelProvider;

  public AttributeFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public AttributeFreebiesBonusPointReducer(IModelCollection modelProvider, ICreditManager creditManager) {
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
      ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, Attributes.MODEL_ID);
      ITraitCollectionModel model = (ITraitCollectionModel) modelProvider.getModel(modelIdentifier);
      Dots dots = new AttributePointCalculator(model, new AttributeGroupConfiguration().getGroups()).dotsFor(handler.getPriority());
      int credit = creditManager.getCredit(characterId, handler.getCreditId());
      favoredSpent += dots.favoredSpentAsPartOfCredit(credit);
      unfavoredSpent += dots.unfavoredSpentAsPartOfCredit(credit);
    }
    int bonusSaved = favoredSpent * IAttributeConstants.FAVORED_BONUS_POINT_COST;
    bonusSaved += unfavoredSpent * IAttributeConstants.BONUS_POINT_COST;
    return -bonusSaved;
  }
}