package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;

@SuppressWarnings("nls")
public class SpiritualTraitCostExtensionPoint_ExperiencePointTest extends AbstractSpiritualTraitCostExtensionPoint_Test {

  public SpiritualTraitCostExtensionPoint_ExperiencePointTest() {
    super("experience");
  }

  @Override
  protected int getPoints(IEssenceCostMap costExtensionPoint, String configurationName) {
    return costExtensionPoint.getEssenceExperienceCost(configurationName);
  }
}