package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.willpower.WillpowerXpCalculatorFactory;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.ICalculator;

public class WillpowerXpHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public WillpowerXpHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel model, ICharacterId characterId) {
    ICalculator calculator = new WillpowerXpCalculatorFactory(model).create();
    return calculator.calculate();
  }
}