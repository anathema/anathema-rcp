package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.virtues.VirtuesBonuspointCalculatorFactory;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.PointWiseBonuspointCalculator;

public class VirtuesBonuspointHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public VirtuesBonuspointHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(final ITraitCollectionModel spiritualTraits, final ICharacterId characterId) {
    VirtuesBonuspointCalculatorFactory factory = new VirtuesBonuspointCalculatorFactory(spiritualTraits);
    PointWiseBonuspointCalculator calculator = factory.create();
    return calculator.calculate();
  }
}