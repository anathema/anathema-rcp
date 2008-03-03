package net.sf.anathema.character.freebies.attributes;

import java.util.Map;

import net.sf.anathema.character.attributes.model.AttributeGroupConfiguration;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.repository.ModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;

public class AttributeFreebiesBonusPointReducer extends AbstractPointHandler {

  private static final String HANDLER_TYPE = "attributeFreebies"; //$NON-NLS-1$
  private final ICreditManager creditManager;

  public AttributeFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new ModelResourceHandler(), new CreditManager());
  }

  public AttributeFreebiesBonusPointReducer(
      IModelCollection modelProvider,
      IModelResourceHandler modelResourceHandler,
      ICreditManager creditManager) {
    super(modelProvider, modelResourceHandler, HANDLER_TYPE, IAttributesPluginConstants.MODEL_ID);
    this.creditManager = creditManager;
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId) {
    Map<Priority, Integer> creditByGroup = new AttributePriorityFreebies().get(characterId, creditManager);
    TraitGroup[] attributeGroups = new AttributeGroupConfiguration().getGroups();
    AttributePointCalculator dotsCalculator = new AttributePointCalculator(creditByGroup, attributes, attributeGroups);
    return AttributePointCalculator.calculatePoints(dotsCalculator.getDotsForGroups());
  }
}