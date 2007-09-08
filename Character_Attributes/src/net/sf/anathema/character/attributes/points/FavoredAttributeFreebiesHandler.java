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
  private final IAttributeGroupFreebiesHandler[] groupHandler;

  public FavoredAttributeFreebiesHandler() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FavoredAttributeFreebiesHandler(IModelProvider modelProvider, ICreditManager creditManager) {
    this.modelProvider = modelProvider;
    this.creditManager = creditManager;
    this.groupHandler = new IAttributeGroupFreebiesHandler[] {
        new PrimaryAttributeFreebies(modelProvider),
        new SecondaryAttributeFreebies(modelProvider),
        new TertiaryAttributeFreebies(modelProvider) };
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    IModelIdentifier identifier = new ModelIdentifier(id, Attributes.MODEL_ID);
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelProvider.getModel(identifier);
    AttributePointCalculator calculator = new AttributePointCalculator(attributes, new AttributeTemplate().getGroups());
    int freeFavored = 0;
    for (IAttributeGroupFreebiesHandler handler : groupHandler) {
      int groupCredit = creditManager.getCredit(id, handler.getCreditId());
      freeFavored += calculator.dotsFor(handler.getPriority()).spentOnFavoredInExcessOfCredit(groupCredit);
    }
    return Math.min(credit, freeFavored);
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }
}