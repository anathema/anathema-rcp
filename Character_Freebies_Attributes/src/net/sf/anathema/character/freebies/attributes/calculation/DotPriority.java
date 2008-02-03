package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;

public class DotPriority {
  private Map<Priority, Dots> bestDotsByPriority = new HashMap<Priority, Dots>();
  private int bestPointReduction = Integer.MAX_VALUE;

  public void set(Map<Priority, Dots> dotsByPriority, int pointReduction) {
    if (bestPointReduction > pointReduction) {
      exchange(dotsByPriority, pointReduction);
    }
  }

  private void exchange(Map<Priority, Dots> dotsByPriority, int pointReduction) {
    bestPointReduction = pointReduction;
    bestDotsByPriority = dotsByPriority;
  }

  public Dots get(Priority priority) {
    return bestDotsByPriority.get(priority);
  }

  public Dots[] getAllDots() {
    Collection<Dots> values = bestDotsByPriority.values();
    return values.toArray(new Dots[values.size()]);
  }
}