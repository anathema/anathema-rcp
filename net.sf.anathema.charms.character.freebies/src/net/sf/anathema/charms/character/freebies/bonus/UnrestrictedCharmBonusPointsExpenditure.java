package net.sf.anathema.charms.character.freebies.bonus;

import net.sf.anathema.character.points.cost.CostDto;

public class UnrestrictedCharmBonusPointsExpenditure {

  private final CostDto costDto;
  private final int credit;

  public UnrestrictedCharmBonusPointsExpenditure(CostDto costDto, int credit) {
    this.costDto = costDto;
    this.credit = credit;
  }

  public int calculate(int cheapCount, int expensiveCount) {
    int relevantExpensive = Math.min(expensiveCount, credit);
    int remainingCredit = credit - relevantExpensive;
    int relevantCheap = Math.min(cheapCount, remainingCredit);
    return costDto.bonus.cheap * relevantCheap + costDto.bonus.expensive * relevantExpensive;
  }
}