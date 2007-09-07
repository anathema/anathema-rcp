package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.trait.group.ITraitGroup;

public class AttributeGroupPoints {

  private final int pointsSpent;
  private final ITraitGroup group;

  public AttributeGroupPoints(int pointsSpent, ITraitGroup group) {
    this.pointsSpent = pointsSpent;
    this.group = group;
  }

  public int getPointsSpent() {
    return pointsSpent;
  }
  
  public ITraitGroup getGroup() {
    return group;
  }
}