package net.sf.anathema.character.freebies.attributes.calculation;

import java.util.ArrayList;
import java.util.Collections;
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
  private DotPriority dots;

  public AttributePointCalculator(
      Map<PriorityGroup, Integer> creditByGroup,
      ITraitCollectionModel attributes,
      ITraitGroup[] groups) {
    this.creditByGroup = creditByGroup;
    this.attributes = attributes;
    this.groups = groups;
  }

  public Dots dotsFor(PriorityGroup priority) {
    return getDots().get(priority);
  }

  public Dots[] getDotsForGroups() {
    return getDots().getAllDots();
  }
  
  private DotPriority getDots() {
    if (dots != null) {
      return dots;
    }
    PriorityGroup[] priorityGroups = PriorityGroup.values();
    DotPriority dotPriority = new DotPriority();
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
        dotPriority.set(new PriorityGroup[] { firstGroup, secondGroup, thirdGroup }, currentDots, pointReduction);
      }
    }
    return dotPriority;
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