package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;

public class FavoredAttributeFreebiesHandler extends AbstractExecutableExtension implements IFreebiesHandler {
  private static final String CREDIT_ID = "net.sf.anthema.character.attributes.freebies.favored"; //$NON-NLS-1$
  private final IModelProvider modelProvider;
  private final ICreditManager creditManager;

  public FavoredAttributeFreebiesHandler() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FavoredAttributeFreebiesHandler(IModelProvider modelProvider, ICreditManager creditManager) {
    this.modelProvider = modelProvider;
    this.creditManager = creditManager;
  }

  @Override
  // TODO Aufräumen und testen & Reducer anmelden
  public int getPoints(ICharacterId id, int credit) {
    IModelIdentifier identifier = new ModelIdentifier(id, Attributes.MODEL_ID);
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelProvider.getModel(identifier);
    AttributePointCalculator pointCalculator = new AttributePointCalculator(
        attributes,
        new AttributeTemplate().getGroups());
    int freeFavored = getAdditionalPointsSpentOnFavored(
        id,
        attributes,
        pointCalculator.groupPointsFor(AttributePointCalculator.PRIMARY),
        new PrimaryAttributeFreebies(modelProvider));
    freeFavored += getAdditionalPointsSpentOnFavored(
        id,
        attributes,
        pointCalculator.groupPointsFor(AttributePointCalculator.SECONDARY),
        new SecondaryAttributeFreebies(modelProvider));
    freeFavored += getAdditionalPointsSpentOnFavored(
        id,
        attributes,
        pointCalculator.groupPointsFor(AttributePointCalculator.TERTIARY),
        new TertiaryAttributeFreebies(modelProvider));
    return Math.min(credit, freeFavored);
  }

  private int getAdditionalPointsSpentOnFavored(
      ICharacterId id,
      ITraitCollectionModel attributes,
      AttributeGroupPoints groupPoints,
      IFreebiesHandler handler) {
    int primaryCredit = creditManager.getCredit(id, handler.getCreditId());
    int pointsSpentOnFavored = getPointsSpentOnFavored(attributes, groupPoints.getGroup());
    return Math.min(pointsSpentOnFavored, Math.max(groupPoints.getPointsSpent() - primaryCredit, 0));
  }

  private int getPointsSpentOnFavored(ITraitCollectionModel attributes, ITraitGroup group) {
    int points = 0;
    for (String traitId : group.getTraitIds()) {
      IBasicTrait trait = attributes.getTrait(traitId);
      if (trait.getFavoredModel().getValue()) {
        int creationValue = trait.getCreationModel().getValue();
        points += Math.max(0, creationValue - IAttributeConstants.ATTRIBUTE_CALCULATION_BASE);
      }
    }
    return points;
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }
}