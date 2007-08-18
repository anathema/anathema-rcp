package net.sf.anathema.character.attributes.points;

import java.util.Arrays;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.trait.group.ITraitGroup;

public class AttributeFreebiesCalculator {

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
  final IAttributes attributes;
  private final ITraitGroup[] groups;

  public AttributeFreebiesCalculator(IAttributes attributes, ITraitGroup[] groups) {
    this.attributes = attributes;
    this.groups = groups;
  }

  public int pointsSpentFor(PriorityGroup priority) {
    AttributeGroupPoints[] groupPoints = ArrayUtilities.transform(
        groups,
        AttributeGroupPoints.class,
        new AttributeGroupPointsTransformer(attributes));
    Arrays.sort(groupPoints, new AscendingPointAttributeGroupComparator());
    return groupPoints[priority.getAscendingIndex()].getPointsSpent();
  }
}