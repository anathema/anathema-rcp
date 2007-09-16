package net.sf.anathema.character.attributes.points;

import java.util.Arrays;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;

public class AttributePointCalculator {

  public static class PriorityGroup {

    private final int ascendingIndex;

    public PriorityGroup(int ascendingIndex) {
      this.ascendingIndex = ascendingIndex;
    }

    public int getAscendingIndex() {
      return ascendingIndex;
    }
  }

  public static final PriorityGroup PRIMARY = new PriorityGroup(0);
  public static final PriorityGroup SECONDARY = new PriorityGroup(1);
  public static final PriorityGroup TERTIARY = new PriorityGroup(2);
  final ITraitCollectionModel attributes;
  private final ITraitGroup[] groups;

  public AttributePointCalculator(ITraitCollectionModel attributes, ITraitGroup[] groups) {
    this.attributes = attributes;
    this.groups = groups;
  }

  public int pointsSpentFor(PriorityGroup priority) {
    Dots group = dotsFor(priority);
    return group.spentTotally();
  }

  public Dots dotsFor(PriorityGroup priority) {
    Dots[] groupPoints = getDotsForGroups();
    Arrays.sort(groupPoints, new AscendingPointAttributeGroupComparator());
    return groupPoints[priority.getAscendingIndex()];
  }

  public Dots[] getDotsForGroups() {
    return ArrayUtilities.transform(groups, Dots.class, new AttributeGroupPointsTransformer(attributes));
  }
}