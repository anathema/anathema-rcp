package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;

public class PrioritylessAttributeFreebies {

  private final IModelProvider modelProvider;
  private final PriorityGroup priority;

  public PrioritylessAttributeFreebies(PriorityGroup priority) {
    this(ModelCache.getInstance(), priority);
  }

  public PrioritylessAttributeFreebies(IModelProvider modelProvider, PriorityGroup priority) {
    this.modelProvider = modelProvider;
    this.priority = priority;
  }

  public int getPoints(ICharacterId id, int credit) {
    TraitGroup[] groups = new AttributeTemplate().getGroups();
    ModelIdentifier modelIdentifier = new ModelIdentifier(id, Attributes.MODEL_ID);
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelProvider.getModel(modelIdentifier);
    AttributePointCalculator calculator = new AttributePointCalculator(attributes, groups);
    return Math.min(credit, calculator.pointsSpentFor(priority));
  }
}