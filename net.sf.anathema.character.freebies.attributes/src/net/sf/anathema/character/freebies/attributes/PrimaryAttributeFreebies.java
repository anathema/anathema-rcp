package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

public class PrimaryAttributeFreebies extends UnconfiguredExecutableExtension implements IAttributeGroupFreebies {

  private static final String CREDIT_ID = "net.sf.anathema.character.attributes.freebies.primary"; //$NON-NLS-1$
  private final PrioritylessAttributeFreebies prioritylessAttributeFreebies;
  private final Priority priority = AttributePointCalculator.Priority.Primary;

  public PrimaryAttributeFreebies() {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies(priority);
  }

  public PrimaryAttributeFreebies(IModelCollection modelProvider, ICreditManager creditManager) {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies(modelProvider, creditManager, priority);
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    return prioritylessAttributeFreebies.getPoints(id, credit);
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }

  public Priority getPriority() {
    return priority;
  }
}