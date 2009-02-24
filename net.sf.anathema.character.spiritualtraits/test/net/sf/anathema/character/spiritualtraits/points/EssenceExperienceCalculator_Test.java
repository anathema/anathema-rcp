package net.sf.anathema.character.spiritualtraits.points;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.essence.EssenceExperienceCalculator;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class EssenceExperienceCalculator_Test {

  private static final int COST = 6;
  private BasicTrait essence;
  private EssenceExperienceCalculator calculator;

  @Before
  public void createCalculatorWithXpCost6() throws Exception {
    this.essence = new BasicTrait(IPluginConstants.ESSENCE_TRAIT_ID);
    ITraitCollectionModel collection = new TraitCollection(essence);
    calculator = new EssenceExperienceCalculator(collection, COST);
  }

  @Test
  public void calculatesNoBonusPointsForStartValue() throws Exception {
    essence.getCreationModel().setValue(1);
    essence.getExperiencedModel().setValue(1);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesNoBonusPointsForValueBelowStartValue() throws Exception {
    essence.getCreationModel().setValue(1);
    essence.getExperiencedModel().setValue(0);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesCostForIncreasingEssenceFrom1To2() throws Exception {
    essence.getCreationModel().setValue(1);
    essence.getExperiencedModel().setValue(2);
    assertThat(calculator.calculate(), is(COST));
  }

  @Test
  public void calculatesCostTimes3ForIncreasingEssenceFrom1To3() throws Exception {
    essence.getCreationModel().setValue(1);
    essence.getExperiencedModel().setValue(3);
    assertThat(calculator.calculate(), is(COST * 3));
  }
}