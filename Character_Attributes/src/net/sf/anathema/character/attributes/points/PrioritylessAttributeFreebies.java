package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.attributes.points.AttributeFreebiesCalculator.PriorityGroup;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.trait.group.TraitGroup;

public class PrioritylessAttributeFreebies {

  private final IModelProvider modelProvider;
  
  public PrioritylessAttributeFreebies() {
    this(new ModelCache());
  }
  
  public PrioritylessAttributeFreebies(IModelProvider modelProvider) {
    this.modelProvider = modelProvider;
  }

  public int getPoints(ICharacterId id, PriorityGroup priority, int credit) {
    TraitGroup[] groups = new AttributeTemplate().getGroups();
    IAttributes attributes = (IAttributes) modelProvider.getModel(new ModelIdentifier(id, IAttributes.MODEL_ID));
    AttributeFreebiesCalculator calculator = new AttributeFreebiesCalculator(attributes, groups);
    return Math.min(credit, calculator.pointsSpentFor(priority));
  }
}