package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.virtues.VirtuesXpCalculatorFactory;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.lang.ICalculator;

public class VirtuesXpHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public VirtuesXpHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(final ITraitCollectionModel spiritualTraits, final ICharacterId characterId) {
    final VirtuesXpCalculatorFactory factory = new VirtuesXpCalculatorFactory(spiritualTraits);
    final ICalculator calculator = factory.create();
    return calculator.calculate();
  }
}