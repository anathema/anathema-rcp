package net.sf.anathema.character.spiritualtraits.points.willpower;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.lib.lang.ICalculator;

import org.junit.Before;
import org.junit.Test;

public class WillpowerXpCalculatorFactory_Test {

  private ICalculator calculator;
  private BasicTrait willpower;
  private TraitCollection collection;

  @Before
  public void createCollection() throws Exception {
    final BasicTrait trait = new BasicTrait(WILLPOWER_ID);
    setCreationValue(trait, 2);
    this.willpower = trait;
    this.collection = new TraitCollection(willpower);
    this.calculator = new WillpowerXpCalculatorFactory(collection).create();
  }

  private final void setCreationValue(final IBasicTrait trait, final int value) {
    trait.getCreationModel().setValue(value);
  }

  @Test
  public void calculatesNoXpForStartingWillpower() throws Exception {
    assertThat(calculator.calculate(), is(0));
  }
}