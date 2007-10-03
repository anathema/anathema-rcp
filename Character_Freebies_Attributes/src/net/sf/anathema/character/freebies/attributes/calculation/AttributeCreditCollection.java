package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.attributes.PrimaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.SecondaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.TertiaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

public class AttributeCreditCollection implements IAttributeCreditCollection {

  private final Map<PriorityGroup, Integer> creditByPriority = new HashMap<PriorityGroup, Integer>();

  public AttributeCreditCollection(ICreditManager creditManager, ICharacterId characterId) {
    for (PriorityGroup group : PriorityGroup.values()) {
      String creditId = determineCreditId(group);
      int credit = creditManager.getCredit(characterId, creditId);
      creditByPriority.put(group, credit);
    }
  }

  private String determineCreditId(PriorityGroup priority) {
    switch (priority) {
      case Primary:
        return new PrimaryAttributeFreebies().getCreditId();
      case Secondary:
        return new SecondaryAttributeFreebies().getCreditId();
      case Tertiary:
        return new TertiaryAttributeFreebies().getCreditId();
    }
    throw new UnreachableCodeReachedException();
  }

  public int getCredit(PriorityGroup priorityGroup) {
    return creditByPriority.get(priorityGroup);
  }
}