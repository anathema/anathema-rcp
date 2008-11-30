package net.sf.anathema.charms.character.freebies.bonus;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.points.cost.CostDto;

import org.junit.Before;
import org.junit.Test;

public class UnrestrictedCharmBonusPointsExpenditure_Test {

  private static final int EXPENSIVE_COST = 5;
  private static final int CHEAP_COST = 2;
  private static final int CREDIT = 3;
  private UnrestrictedCharmBonusPointsExpenditure expenditure;

  @Before
  public void createExpenditure() {
    CostDto cost = new CostDto();
    cost.experience.cheap = Integer.MAX_VALUE;
    cost.experience.expensive = Integer.MAX_VALUE;
    cost.bonus.cheap = CHEAP_COST;
    cost.bonus.expensive = EXPENSIVE_COST;
    expenditure = new UnrestrictedCharmBonusPointsExpenditure(cost, CREDIT);
  }

  @Test
  public void calculatesExpensiveCostForExpensiveCharm() throws Exception {
    assertThat(expenditure.calculate(0, 1), is(EXPENSIVE_COST));
  }

  @Test
  public void calculatesCheapCostForCheapCharm() throws Exception {
    assertThat(expenditure.calculate(1, 0), is(CHEAP_COST));
  }

  @Test
  public void calculatesCostOnlyForCreditExpensiveCharms() throws Exception {
    assertThat(expenditure.calculate(0, CREDIT + 1), is(EXPENSIVE_COST * CREDIT));
  }

  @Test
  public void calculatesCostOnlyForCreditCheapCharms() throws Exception {
    assertThat(expenditure.calculate(CREDIT + 1, 0), is(CHEAP_COST * CREDIT));
  }

  @Test
  public void prefersExpensiveOverCheapCharms() throws Exception {
    assertThat(expenditure.calculate(1, CREDIT), is(EXPENSIVE_COST * CREDIT));
  }
}