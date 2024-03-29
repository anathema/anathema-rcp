package net.sf.anathema.character.freebies.attributes;

import java.util.Map;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FavoredAttributeFreebiesHandler extends UnconfiguredExecutableExtension implements IFreebiesHandler {
  private static final String CREDIT_ID = "net.sf.anthema.character.attributes.freebies.favored"; //$NON-NLS-1$
  private final IModelCollection modelProvider;
  private final ICreditManager creditManager;

  public FavoredAttributeFreebiesHandler() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FavoredAttributeFreebiesHandler(IModelCollection modelProvider, ICreditManager creditManager) {
    this.modelProvider = modelProvider;
    this.creditManager = creditManager;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    IModelIdentifier identifier = new ModelIdentifier(id, IAttributesPluginConstants.MODEL_ID);
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelProvider.getModel(identifier);
    Map<Priority, Integer> creditsByGroup = new AttributePriorityFreebies().get(id, creditManager);
    AttributePointCalculator calculator = new AttributePointCalculator(
        creditsByGroup,
        attributes,
        new AttributeGroupTemplate().getGroups());
    int freeFavored = 0;
    for (Dots dots : calculator.getDotsForGroups()) {
      freeFavored += dots.spentOnCheapInExcessOfCredit();
    }
    return Math.min(credit, freeFavored);
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }
}