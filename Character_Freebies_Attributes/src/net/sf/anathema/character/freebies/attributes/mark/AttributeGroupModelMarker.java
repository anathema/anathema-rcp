package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;

public class AttributeGroupModelMarker implements IModelMarker {

  private final IAttributeCreditCollection creditCollection;
  private final ITotalDotsSpent dotsSpent;
  private final PriorityGroup priorityGroup;
  private final String markerId;

  public AttributeGroupModelMarker(
      IAttributeCreditCollection creditCollection,
      ITotalDotsSpent dotsSpent,
      PriorityGroup priorityGroup,
      String markerId) {
    this.creditCollection = creditCollection;
    this.dotsSpent = dotsSpent;
    this.priorityGroup = priorityGroup;
    this.markerId = markerId;
  }

  @Override
  public String getMarkerId() {
    return markerId;
  }

  @Override
  public boolean isActive() {
    return creditCollection.getCredit(priorityGroup) > dotsSpent.get(priorityGroup);
  }
}