package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;

public class DotPriority {
  private Map<PriorityGroup, Dots> bestDots = new HashMap<PriorityGroup, Dots>();
  private int bestPointReduction = Integer.MAX_VALUE;

  public void set(PriorityGroup[] priorityGroups, Dots[] currentDots, int pointReduction) {
    if (bestPointReduction > pointReduction) {
      bestPointReduction = pointReduction;
      bestDots.clear();
      for (int index = 0; index < priorityGroups.length; index++) {
        bestDots.put(priorityGroups[index], currentDots[index]);
      }
    }
  }

  public Dots get(PriorityGroup priority) {
    return bestDots.get(priority);
  }

  public Dots[] getAllDots() {
    Collection<Dots> values = bestDots.values();
    return values.toArray(new Dots[values.size()]);
  }
}