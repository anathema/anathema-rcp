package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.attributes.points.AttributeFreebiesCalculator.PriorityGroup;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.trait.group.TraitGroup;

public class PrioritylessFreebiesHandler {

  private final ModelCache modelCache = new ModelCache();

  public int getPoints(ICharacterId id, PriorityGroup priority) {
    IAttributes attributes = (IAttributes) modelCache.getModel(new ModelIdentifier(id, IAttributes.MODEL_ID));
    TraitGroup[] groups = new AttributeTemplate().getGroups();
    AttributeFreebiesCalculator calculator = new AttributeFreebiesCalculator(attributes, groups);
    return calculator.pointsSpentFor(priority);
  }
}