package net.sf.anathema.character.attributes.points;

import java.util.Comparator;

public final class AscendingPointAttributeGroupComparator implements Comparator<AttributeGroupPoints> {
  @Override
  public int compare(AttributeGroupPoints o1, AttributeGroupPoints o2) {
    return o2.getPointsSpent() - o1.getPointsSpent();
  }
}