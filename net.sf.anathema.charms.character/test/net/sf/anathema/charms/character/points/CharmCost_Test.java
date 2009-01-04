package net.sf.anathema.charms.character.points;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.disy.commons.core.predicate.AcceptAllPredicate;
import net.disy.commons.core.predicate.AcceptNothingPredicate;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.points.cost.CostDto;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharmCost_Test {

  private CostDto costDto;
  private IPredicate<ICharmId> cheapPredicate;
  private IPredicate<ICharmId> expensivePredicate;

  @Before
  public void createCostDto() {
    costDto = new CostDto();
    costDto.bonus.cheap = 4;
    costDto.bonus.expensive = 5;
    costDto.experience.cheap = 6;
    costDto.experience.expensive = 7;
  }

  @Before
  public void createPredicates() {
    cheapPredicate = new AcceptAllPredicate<ICharmId>();
    expensivePredicate = new AcceptNothingPredicate<ICharmId>();
  }

  @Test
  public void returnsCheapBonusCostForCheapCharm() throws Exception {
    assertThat(new CharmCosts(cheapPredicate, costDto).getBonusPointCost(null), is(4));
  }

  @Test
  public void returnsExpensiveBonusCostForExpensiveCharm() throws Exception {
    assertThat(new CharmCosts(expensivePredicate, costDto).getBonusPointCost(null), is(5));
  }

  @Test
  public void returnsCheapExperienceCostForCheapCharm() throws Exception {
    assertThat(new CharmCosts(cheapPredicate, costDto).getExperienceCost(null), is(6));
  }

  @Test
  public void returnsExpensiveExperienceCostForExpensiveCharm() throws Exception {
    assertThat(new CharmCosts(expensivePredicate, costDto).getExperienceCost(null), is(7));
  }
}