package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.model.AttributeGroupConfiguration;
import net.sf.anathema.character.attributes.points.AbstractPointHandler;
import net.sf.anathema.character.attributes.points.IAttributeConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.repository.ModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeFreebiesBonusPointReducer extends AbstractPointHandler {

  private static final String HANDLER_TYPE = "attributeFreebies"; //$NON-NLS-1$
  private final ICreditManager creditManager;
  private final IAttributeGroupFreebies[] freebiesHandlers;

  public AttributeFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new ModelResourceHandler(), new CreditManager());
  }

  public AttributeFreebiesBonusPointReducer(
      IModelCollection modelProvider,
      IModelResourceHandler modelResourceHandler,
      ICreditManager creditManager) {
    super(modelProvider, modelResourceHandler, HANDLER_TYPE);
    this.creditManager = creditManager;
    this.freebiesHandlers = new IAttributeGroupFreebies[] {
        new PrimaryAttributeFreebies(modelProvider),
        new SecondaryAttributeFreebies(modelProvider),
        new TertiaryAttributeFreebies(modelProvider) };
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId) {
    int favoredSpent = 0;
    int unfavoredSpent = 0;
    for (IAttributeGroupFreebies handler : freebiesHandlers) {
      Dots dots = new AttributePointCalculator(attributes, new AttributeGroupConfiguration().getGroups()).dotsFor(handler.getPriority());
      int credit = creditManager.getCredit(characterId, handler.getCreditId());
      favoredSpent += dots.cheaplySpentAsPartOfCredit(credit);
      unfavoredSpent += dots.expensivlySpentAsPartOfCredit(credit);
    }
    int bonusSaved = favoredSpent * IAttributeConstants.FAVORED_BONUS_POINT_COST;
    bonusSaved += unfavoredSpent * IAttributeConstants.BONUS_POINT_COST;
    return -bonusSaved;
  }
}