package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.Map;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.attributes.AttributePriorityFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

public class AttributeCreditCollection implements IAttributeCreditCollection {

  private final Map<Priority, Integer> creditByPriority;

  public AttributeCreditCollection(ICreditManager creditManager, ICharacterId characterId) {
    creditByPriority = new AttributePriorityFreebies().get(characterId, creditManager);
  }

  public int getCredit(Priority priorityGroup) {
    return creditByPriority.get(priorityGroup);
  }
}