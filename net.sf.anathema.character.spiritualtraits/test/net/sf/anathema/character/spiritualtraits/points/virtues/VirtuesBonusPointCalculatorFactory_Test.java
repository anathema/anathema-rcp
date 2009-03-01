package net.sf.anathema.character.spiritualtraits.points.virtues;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.points.ICalculator;

import org.junit.Before;
import org.junit.Test;

public class VirtuesBonusPointCalculatorFactory_Test {

  private IBasicTrait compassion;
  private IBasicTrait conviction;
  private IBasicTrait temperance;
  private IBasicTrait valor;
  private IBasicTrait essence;
  private ICalculator calculator;

  @Before
  public void createCalculator() throws Exception {
    this.compassion = createVirtue(COMPASSION_ID);
    this.conviction = createVirtue(CONVICTION_ID);
    this.temperance = createVirtue(TEMPERANCE_ID);
    this.valor = createVirtue(VALOR_ID);
    this.essence = new BasicTrait(ESSENCE_TRAIT_ID);
    final TraitCollection collection = new TraitCollection(essence, compassion, conviction, temperance, valor);
    this.calculator = new VirtuesBonuspointCalculatorFactory(collection).create();
  }

  private BasicTrait createVirtue(final String id) {
    final BasicTrait trait = new BasicTrait(id);
    setValue(trait, 1);
    return trait;
  }

  private void setValue(final IBasicTrait trait, final int value) {
    trait.getCreationModel().setValue(value);
  }

  @Test
  public void calculatesNoBonusPointsForIncreasedEssence() throws Exception {
    setIncreased(essence);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedCompassion() throws Exception {
    setIncreased(compassion);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedConviction() throws Exception {
    setIncreased(conviction);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedTemperance() throws Exception {
    setIncreased(temperance);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedValor() throws Exception {
    setIncreased(valor);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void sumsUpBonusPointsForIncrements() throws Exception {
    setIncreased(valor);
    setIncreased(temperance);
    assertThat(calculator.calculate(), is(6));
  }

  private void setIncreased(final IBasicTrait trait) {
    setValue(trait, 2);
  }
}