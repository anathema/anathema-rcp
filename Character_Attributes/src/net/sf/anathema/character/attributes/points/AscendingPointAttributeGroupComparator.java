package net.sf.anathema.character.attributes.points;

import java.util.Comparator;

public final class AscendingPointAttributeGroupComparator implements Comparator<Dots> {
  @Override
  public int compare(Dots o1, Dots o2) {
    return o2.spentTotally() - o1.spentTotally();
  }
}