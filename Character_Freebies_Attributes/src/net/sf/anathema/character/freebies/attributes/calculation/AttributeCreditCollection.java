package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.Map;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.attributes.AttributePriorityFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

public class AttributeCreditCollection implements IAttributeCreditCollection {

  private final Map<PriorityGroup, Integer> creditByPriority;

  public AttributeCreditCollection(ICreditManager creditManager, ICharacterId characterId) {
    creditByPriority = new AttributePriorityFreebies().get(characterId, creditManager);
  }

  public int getCredit(PriorityGroup priorityGroup) {
    return creditByPriority.get(priorityGroup);
  }
}