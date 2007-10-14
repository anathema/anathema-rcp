package net.sf.anathema.character.freebies.attributes.mark;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;

public class AttributeGroupModelMarker implements IModelMarker {

  private final IAttributeCreditCollection creditCollection;
  private final ITotalDotsSpent dotsSpent;
  private final PriorityGroup priorityGroup;
  private final String markerId;
  private final Map<PriorityGroup, String> messageFormatByGroup = new HashMap<PriorityGroup, String>() {
    {
      put(PriorityGroup.Primary, Messages.AttributeGroupModelMarker_UnspentPrimaryDescription);
      put(PriorityGroup.Secondary, Messages.AttributeGroupModelMarker_UnspentSecondaryDescription);
      put(PriorityGroup.Tertiary, Messages.AttributeGroupModelMarker_UnspentTertiaryDescription);
    }
  };

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

  @Override
  public String getDescription(String characterName) {
    return MessageFormat.format(messageFormatByGroup.get(priorityGroup), characterName);
  }
}