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
import net.sf.anathema.character.trait.group.TraitGroup;

public class FavoredAttributeFreebiesHandler extends AbstractExecutableExtension implements IFreebiesHandler {
  private static final String CREDIT_ID = "net.sf.anthema.character.attributes.freebies.favored"; //$NON-NLS-1$
  private final IModelProvider modelProvider;
  private final IFreebiesHandler[] freebiesHandlers;
  private final ICreditManager creditManager;

  public FavoredAttributeFreebiesHandler() {
    this(ModelCache.getInstance(), new CreditManager());
  }
  
  public FavoredAttributeFreebiesHandler(IModelProvider modelProvider, ICreditManager creditManager) {
    this.modelProvider = modelProvider;
    this.creditManager = creditManager;
    this.freebiesHandlers = new IFreebiesHandler[] {
        new PrimaryAttributeFreebies(modelProvider),
        new SecondaryAttributeFreebies(modelProvider),
        new TertiaryAttributeFreebies(modelProvider) };
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    IModelIdentifier modelIdentifer = new ModelIdentifier(id, Attributes.MODEL_ID);
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelProvider.getModel(modelIdentifer);
    TraitGroup[] traitGroups = new AttributeTemplate().getGroups();
    AttributePointCalculator pointCalculator = new AttributePointCalculator(attributes, traitGroups);
    int count = 0;
    for (IFreebiesHandler handler : freebiesHandlers) {
      int handlerCredit = creditManager.getCredit(id, handler.getCreditId());
      int points = handler.getPoints(id, credit);
    }
    return count;
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }
}