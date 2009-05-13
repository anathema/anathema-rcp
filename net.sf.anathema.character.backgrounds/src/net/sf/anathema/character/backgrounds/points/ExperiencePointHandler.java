package net.sf.anathema.character.backgrounds.points;

import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.PointWiseExperienceCalculator;
import net.sf.anathema.lib.lang.ICalculator;

public class ExperiencePointHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public ExperiencePointHandler() {
    super(IBackgroundModel.MODEL_ID);
  }

  @Override
  protected int calculatePoints(final ITraitCollectionModel traits, final ICharacterId characterId) {
    Iterable<IBasicTrait> backgrounds = traits.getAllTraits();
    ICalculator calculator = new PointWiseExperienceCalculator(3, backgrounds);
    return calculator.calculate();
  }
}