package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.attributes.points.IAttributeConstants;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.lib.util.IIdentificate;

public class AttributePointCalculator {

  public enum Priority {

    Primary(0), Secondary(1), Tertiary(2);

    private final int ascendingIndex;

    private Priority(int ascendingIndex) {
      this.ascendingIndex = ascendingIndex;
    }

    public int getAscendingIndex() {
      return ascendingIndex;
    }
  }

  final ITraitCollectionModel traitCollection;
  private final ITraitGroup[] traitGroups;
  private final Map<Priority, Integer> creditByPriorityGroup;
  private DotPriority dotPriority;

  public AttributePointCalculator(
      Map<Priority, Integer> creditByGroup,
      ITraitCollectionModel attributes,
      ITraitGroup[] groups) {
    this.creditByPriorityGroup = creditByGroup;
    this.traitCollection = attributes;
    this.traitGroups = groups;
  }

  public Dots dotsFor(Priority priority) {
    return getDots().get(priority);
  }

  public Dots[] getDotsForGroups() {
    return getDots().getAllDots();
  }
  
  private DotPriority getDots() {
    if (dotPriority != null) {
      return dotPriority;
    }
    Priority[] allPriorities = Priority.values();
    DotPriority newPriority = new DotPriority();
    for (Priority firstPriority : allPriorities) {
      for (Priority secondPriority : allPriorities) {
        if (firstPriority == secondPriority) {
          continue;
        }
        Priority thirdPriority = getLastGroup(firstPriority, secondPriority);
        Dots[] currentDots = new Dots[] {
            new Dots(creditByPriorityGroup.get(firstPriority), traitCollection, traitGroups[0]),
            new Dots(creditByPriorityGroup.get(secondPriority), traitCollection, traitGroups[1]),
            new Dots(creditByPriorityGroup.get(thirdPriority), traitCollection, traitGroups[2]) };
        int pointReduction = calculatePoints(currentDots);
        Map<Priority, Dots> dotsByPriority = new HashMap<Priority, Dots>();
        dotsByPriority.put(firstPriority, currentDots[0]);
        dotsByPriority.put(secondPriority, currentDots[1]);
        dotsByPriority.put(thirdPriority, currentDots[2]);
        newPriority.set(dotsByPriority, pointReduction);
      }
    }
    return newPriority;
  }

  private Priority getLastGroup(Priority firstGroup, Priority secondGroup) {
    List<Priority> allGroups = new ArrayList<Priority>();
    Collections.addAll(allGroups, Priority.values());
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

  public Dots getDotsFor(IIdentificate traitType) {
    for (Dots dots : getDotsForGroups()) {
      if (dots.containsTrait(traitType)) {
        return dots;
      }
    }
    return new Dots(0);
  }
}