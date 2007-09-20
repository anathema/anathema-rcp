package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.points.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;

public class TertiaryAttributeFreebies extends AbstractExecutableExtension implements IAttributeGroupFreebiesHandler {
  private static final String CREDIT_ID = "net.sf.anathema.character.attributes.freebies.tertiary"; //$NON-NLS-1$
  private final PrioritylessAttributeFreebies prioritylessAttributeFreebies;
  private final PriorityGroup priority = AttributePointCalculator.PriorityGroup.Tertiary;

  public TertiaryAttributeFreebies() {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies(priority);
  }
  
  public TertiaryAttributeFreebies(IModelProvider modelProvider) {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies(modelProvider, priority);
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    return prioritylessAttributeFreebies.getPoints(id, credit);
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }
  
  @Override
  public PriorityGroup getPriority() {
    return priority;
  }
}