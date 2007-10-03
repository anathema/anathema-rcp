package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;

public class AttributeGroupMarkerId {

  private final PriorityGroup priorityGroup;
  private final String id;

  public AttributeGroupMarkerId(PriorityGroup priorityGroup, String id) {
    this.priorityGroup = priorityGroup;
    this.id = id;
  }

  public String getId() {
    return id;
  }
  
  public PriorityGroup getPriority() {
    return priorityGroup;
  }
}