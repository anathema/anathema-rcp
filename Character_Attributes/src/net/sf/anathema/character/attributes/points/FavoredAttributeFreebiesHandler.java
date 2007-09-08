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
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

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
    AttributePointCalculator calculator = new AttributePointCalculator(attributes, new AttributeTemplate().getGroups());
    int freeFavored = getAdditionalPointsSpentOnFavored(
        id,
        calculator.dotsFor(AttributePointCalculator.PRIMARY),
        new PrimaryAttributeFreebies(modelProvider));
    freeFavored += getAdditionalPointsSpentOnFavored(
        id,
        calculator.dotsFor(AttributePointCalculator.SECONDARY),
        new SecondaryAttributeFreebies(modelProvider));
    freeFavored += getAdditionalPointsSpentOnFavored(
        id,
        calculator.dotsFor(AttributePointCalculator.TERTIARY),
        new TertiaryAttributeFreebies(modelProvider));
    return Math.min(credit, freeFavored);
  }

  private int getAdditionalPointsSpentOnFavored(ICharacterId id, Dots groupDots, IFreebiesHandler handler) {
    int groupCredit = creditManager.getCredit(id, handler.getCreditId());
    return groupDots.spentOnFavoredInExcessOfCredit(groupCredit);
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }
}