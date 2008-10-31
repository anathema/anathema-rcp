package net.sf.anathema.character.freebies.attributes;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

public class AttributePriorityFreebies {

  private static final String PRIMARY_CREDIT_ID = "net.sf.anathema.character.attributes.freebies.primary"; //$NON-NLS-1$
  private static final String SECONDARY_CREDIT_ID = "net.sf.anathema.character.attributes.freebies.secondary"; //$NON-NLS-1$
  private static final String TERTIARY_CREDIT_ID = "net.sf.anathema.character.attributes.freebies.tertiary"; //$NON-NLS-1$

  public Map<Priority, Integer> get(ICharacterId characterId, ICreditManager manager) {
    Map<Priority, Integer> creditByGroup = new HashMap<Priority, Integer>();
    creditByGroup.put(Priority.Primary, manager.getCredit(characterId, PRIMARY_CREDIT_ID));
    creditByGroup.put(Priority.Secondary, manager.getCredit(characterId, SECONDARY_CREDIT_ID));
    creditByGroup.put(Priority.Tertiary, manager.getCredit(characterId, TERTIARY_CREDIT_ID));
    return creditByGroup;
  }

  public Map<Priority, Integer> getEmpty() {
    Map<Priority, Integer> creditByGroup = new HashMap<Priority, Integer>();
    creditByGroup.put(Priority.Primary, 0);
    creditByGroup.put(Priority.Secondary, 0);
    creditByGroup.put(Priority.Tertiary, 0);
    return creditByGroup;
  }
}