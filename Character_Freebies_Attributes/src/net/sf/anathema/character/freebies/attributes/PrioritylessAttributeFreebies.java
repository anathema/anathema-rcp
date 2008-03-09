package net.sf.anathema.character.freebies.attributes;

import java.util.Map;

import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;

public class PrioritylessAttributeFreebies {

  private final IModelCollection modelProvider;
  private final Priority priority;
  private final ICreditManager creditManager;

  public PrioritylessAttributeFreebies(Priority priority) {
    this(ModelCache.getInstance(), new CreditManager(), priority);
  }

  public PrioritylessAttributeFreebies(
      IModelCollection modelProvider,
      ICreditManager creditManager,
      Priority priority) {
    this.modelProvider = modelProvider;
    this.creditManager = creditManager;
    this.priority = priority;
  }

  public int getPoints(ICharacterId id, int credit) {
    Map<Priority, Integer> creditsByGroup = new AttributePriorityFreebies().get(id, creditManager);
    TraitGroup[] groups = new AttributeGroupTemplate().getGroups();
    ModelIdentifier modelIdentifier = new ModelIdentifier(id, IAttributesPluginConstants.MODEL_ID);
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelProvider.getModel(modelIdentifier);
    AttributePointCalculator calculator = new AttributePointCalculator(creditsByGroup, attributes, groups);
    return Math.min(credit, calculator.dotsFor(priority).spentTotally());
  }
}