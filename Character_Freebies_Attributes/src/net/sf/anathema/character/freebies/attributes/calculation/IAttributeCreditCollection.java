package net.sf.anathema.character.freebies.attributes.calculation;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;

public interface IAttributeCreditCollection {

  public int getCredit(PriorityGroup priorityGroup);

}