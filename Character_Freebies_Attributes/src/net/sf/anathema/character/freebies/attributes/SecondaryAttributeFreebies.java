package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.freebies.attributes.AttributePointCalculator.PriorityGroup;

public class SecondaryAttributeFreebies extends AbstractExecutableExtension implements IAttributeGroupFreebiesHandler {
  private static final String CREDIT_ID = "net.sf.anathema.character.attributes.freebies.secondary"; //$NON-NLS-1$
  private final PrioritylessAttributeFreebies prioritylessAttributeFreebies;
  private final PriorityGroup priority = AttributePointCalculator.PriorityGroup.Secondary;

  public SecondaryAttributeFreebies() {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies(priority);
  }
  
  public SecondaryAttributeFreebies(IModelProvider modelProvider) {
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