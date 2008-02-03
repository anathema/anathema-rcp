package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;

public class DotPriority {
  private Map<Priority, Dots> bestDots = new HashMap<Priority, Dots>();
  private int bestPointReduction = Integer.MAX_VALUE;

  public void set(Priority[] priorities, Dots[] dots, int pointReduction) {
    if (bestPointReduction > pointReduction) {
      exchange(priorities, dots, pointReduction);
    }
  }

  private void exchange(Priority[] priorityGroups, Dots[] currentDots, int pointReduction) {
    bestPointReduction = pointReduction;
    bestDots.clear();
    for (int index = 0; index < priorityGroups.length; index++) {
      bestDots.put(priorityGroups[index], currentDots[index]);
    }
  }

  public Dots get(Priority priority) {
    return bestDots.get(priority);
  }

  public Dots[] getAllDots() {
    Collection<Dots> values = bestDots.values();
    return values.toArray(new Dots[values.size()]);
  }
}