package net.sf.anathema.character.spiritualtraits.points;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.essence.EssenceBonuspointCalculator;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.points.PointwiseCostDto;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class EssenceBonuspointCalculator_Test {

  private BasicTrait otherTrait;
  private BasicTrait essence;
  private PointwiseCostDto costDto;
  private EssenceBonuspointCalculator calculator;

  @Before
  public void createCalculatorWithTwoTraitsOnEssenceStartValue() throws Exception {
    this.otherTrait = new BasicTrait("other");
    this.essence = new BasicTrait(IPluginConstants.ESSENCE_ID);
    ITraitCollectionModel collection = new TraitCollection(otherTrait, essence);
    costDto = new PointwiseCostDto();
    costDto.pointCost = 12;
    costDto.startValue = 2;
    calculator = new EssenceBonuspointCalculator(collection, costDto);
  }

  @Test
  public void calculatesNoBonusPointsForStartValue() throws Exception {
    essence.getCreationModel().setValue(costDto.startValue);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesNoBonusPointsForValueBelowStartValue() throws Exception {
    essence.getCreationModel().setValue(costDto.startValue - 1);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesNoBonusPointsForIncreasedOtherValue() throws Exception {
    otherTrait.getCreationModel().setValue(costDto.startValue + 1);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesPointCostValueForEssenceIncreasedByOne() throws Exception {
    essence.getCreationModel().setValue(costDto.startValue + 1);
    assertThat(calculator.calculate(), is(costDto.pointCost));
  }

  @Test
  public void calculatesMultipliedPointCostValueForMultipleEssenceIncreases() throws Exception {
    essence.getCreationModel().setValue(costDto.startValue + 3);
    assertThat(calculator.calculate(), is(3 * costDto.pointCost));
  }
}