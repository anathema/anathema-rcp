package net.sf.anathema.character.freebies.attributes;

import java.util.Map;

import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;

public class AttributeFreebiesBonusPointReducer extends AbstractPointHandler<ITraitCollectionModel> {

  private final ICreditManager creditManager;

  public AttributeFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public AttributeFreebiesBonusPointReducer(IModelCollection modelProvider, ICreditManager creditManager) {
    super(modelProvider, IAttributesPluginConstants.MODEL_ID);
    this.creditManager = creditManager;
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId) {
    Map<Priority, Integer> creditByGroup = new AttributePriorityFreebies().get(characterId, creditManager);
    TraitGroup[] attributeGroups = new AttributeGroupTemplate().getGroups();
    AttributePointCalculator dotsCalculator = new AttributePointCalculator(creditByGroup, attributes, attributeGroups);
    return AttributePointCalculator.calculatePoints(dotsCalculator.getDotsForGroups());
  }
}