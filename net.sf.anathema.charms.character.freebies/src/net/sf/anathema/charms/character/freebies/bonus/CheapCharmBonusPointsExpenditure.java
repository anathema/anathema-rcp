package net.sf.anathema.charms.character.freebies.bonus;

import net.sf.anathema.character.points.cost.CostDto;

public class CheapCharmBonusPointsExpenditure {

  private final CostDto costDto;

  public CheapCharmBonusPointsExpenditure(CostDto costDto) {
    this.costDto = costDto;
  }

  public int calculate(int cheapFreebiesCount) {
    return cheapFreebiesCount * costDto.bonus.cheap;
  }
}