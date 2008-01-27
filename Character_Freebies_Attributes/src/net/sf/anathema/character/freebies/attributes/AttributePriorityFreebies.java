package net.sf.anathema.character.freebies.attributes;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

public class AttributePriorityFreebies {

  private static final String PRIMARY_CREDIT_ID = "net.sf.anathema.character.attributes.freebies.primary"; //$NON-NLS-1$
  private static final String SECONDARY_CREDIT_ID = "net.sf.anathema.character.attributes.freebies.secondary"; //$NON-NLS-1$
  private static final String TERTIARY_CREDIT_ID = "net.sf.anathema.character.attributes.freebies.tertiary"; //$NON-NLS-1$

  public Map<PriorityGroup, Integer> get(ICharacterId characterId, ICreditManager manager) {
    Map<PriorityGroup, Integer> creditByGroup = new HashMap<PriorityGroup, Integer>();
    creditByGroup.put(PriorityGroup.Primary, manager.getCredit(characterId, PRIMARY_CREDIT_ID));
    creditByGroup.put(PriorityGroup.Secondary, manager.getCredit(characterId, SECONDARY_CREDIT_ID));
    creditByGroup.put(PriorityGroup.Tertiary, manager.getCredit(characterId, TERTIARY_CREDIT_ID));
    return creditByGroup;
  }

  public Map<PriorityGroup, Integer> getEmpty() {
    Map<PriorityGroup, Integer> creditByGroup = new HashMap<PriorityGroup, Integer>();
    creditByGroup.put(PriorityGroup.Primary, 0);
    creditByGroup.put(PriorityGroup.Secondary, 0);
    creditByGroup.put(PriorityGroup.Tertiary, 0);
    return creditByGroup;
  }
}