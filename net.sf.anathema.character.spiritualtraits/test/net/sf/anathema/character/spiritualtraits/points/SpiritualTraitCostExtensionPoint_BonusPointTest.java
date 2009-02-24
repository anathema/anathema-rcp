package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;

@SuppressWarnings("nls")
public class SpiritualTraitCostExtensionPoint_BonusPointTest extends AbstractSpiritualTraitCostExtensionPoint_Test {

  public SpiritualTraitCostExtensionPoint_BonusPointTest() {
    super("bonuspoints");
  }

  @Override
  protected int getPoints(IEssenceCostMap costExtensionPoint, String configurationName) {
    return costExtensionPoint.getEssenceBonuspointCost(configurationName);
  }
}