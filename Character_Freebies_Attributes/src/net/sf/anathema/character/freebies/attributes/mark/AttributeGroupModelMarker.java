package net.sf.anathema.character.freebies.attributes.mark;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.resource.IModelMarker;
import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;

import org.eclipse.core.resources.IMarker;
import org.eclipse.osgi.util.NLS;

public class AttributeGroupModelMarker implements IModelMarker {

  private final IAttributeCreditCollection creditCollection;
  private final ITotalDotsSpent dotsSpent;
  private final Priority priorityGroup;
  private final String markerId;
  private final Map<Priority, String> messageFormatByGroup = new HashMap<Priority, String>() {
    {
      put(Priority.Primary, Messages.AttributeGroupModelMarker_UnspentPrimaryDescription);
      put(Priority.Secondary, Messages.AttributeGroupModelMarker_UnspentSecondaryDescription);
      put(Priority.Tertiary, Messages.AttributeGroupModelMarker_UnspentTertiaryDescription);
    }
  };

  public AttributeGroupModelMarker(
      IAttributeCreditCollection creditCollection,
      ITotalDotsSpent dotsSpent,
      Priority priorityGroup,
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
  public boolean isActive(IMarker[] markers) {
    return creditCollection.getCredit(priorityGroup) > dotsSpent.get(priorityGroup);
  }

  @Override
  public String getDescription(String characterName) {
    return NLS.bind(messageFormatByGroup.get(priorityGroup), characterName);
  }
}