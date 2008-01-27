package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.attributes.points.IAttributeConstants;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;

public class AttributePointCalculator {

  public enum PriorityGroup {

    Primary(0), Secondary(1), Tertiary(2);

    private final int ascendingIndex;

    private PriorityGroup(int ascendingIndex) {
      this.ascendingIndex = ascendingIndex;
    }

    public int getAscendingIndex() {
      return ascendingIndex;
    }
  }

  final ITraitCollectionModel attributes;
  private final ITraitGroup[] groups;
  private final Map<PriorityGroup, Integer> creditByGroup;
  private Map<PriorityGroup, Dots> dots;

  public AttributePointCalculator(
      Map<PriorityGroup, Integer> creditByGroup,
      ITraitCollectionModel attributes,
      ITraitGroup[] groups) {
    this.creditByGroup = creditByGroup;
    this.attributes = attributes;
    this.groups = groups;
  }

  public Dots dotsFor(PriorityGroup priority) {
    getDotsForGroups();
    return dots.get(priority);
  }

  public Dots[] getDotsForGroups() {
    if (dots == null) {
      dots = calculateDots();
    }
    Collection<Dots> values = dots.values();
    return values.toArray(new Dots[values.size()]);
  }

  private Map<PriorityGroup, Dots> calculateDots() {
    PriorityGroup[] priorityGroups = PriorityGroup.values();
    Map<PriorityGroup, Dots> bestDots = new HashMap<PriorityGroup, Dots>();
    int bestPointReduction = Integer.MAX_VALUE;
    for (PriorityGroup firstGroup : priorityGroups) {
      for (PriorityGroup secondGroup : priorityGroups) {
        if (firstGroup == secondGroup) {
          continue;
        }
        PriorityGroup thirdGroup = getLastGroup(firstGroup, secondGroup);
        Dots[] currentDots = new Dots[] {
            new Dots(creditByGroup.get(firstGroup), attributes, groups[0]),
            new Dots(creditByGroup.get(secondGroup), attributes, groups[1]),
            new Dots(creditByGroup.get(thirdGroup), attributes, groups[2]) };
        int pointReduction = calculatePoints(currentDots);
        if (bestPointReduction > pointReduction) {
          bestDots.clear();
          bestDots.put(firstGroup, currentDots[0]);
          bestDots.put(secondGroup, currentDots[1]);
          bestDots.put(thirdGroup, currentDots[2]);
          bestPointReduction = pointReduction;
        }
      }
    }
    return bestDots;
  }

  private PriorityGroup getLastGroup(PriorityGroup firstGroup, PriorityGroup secondGroup) {
    List<PriorityGroup> allGroups = new ArrayList<PriorityGroup>();
    Collections.addAll(allGroups, PriorityGroup.values());
    allGroups.remove(firstGroup);
    allGroups.remove(secondGroup);
    return allGroups.get(0);
  }

  public static int calculatePoints(Dots[] allDots) {
    int favoredSpent = 0;
    int unfavoredSpent = 0;
    for (Dots dots : allDots) {
      favoredSpent += dots.cheaplySpentAsPartOfCredit();
      unfavoredSpent += dots.expensivlySpentAsPartOfCredit();
    }
    int bonusSaved = favoredSpent * IAttributeConstants.FAVORED_BONUS_POINT_COST;
    bonusSaved += unfavoredSpent * IAttributeConstants.BONUS_POINT_COST;
    return -bonusSaved;
  }
}