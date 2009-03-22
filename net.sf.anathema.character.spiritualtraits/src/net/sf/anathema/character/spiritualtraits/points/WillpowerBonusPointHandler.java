package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.virtues.VirtueSumCalculator;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class WillpowerBonusPointHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public WillpowerBonusPointHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(final ITraitCollectionModel spiritualTraits, final ICharacterId characterId) {
    IBasicTrait willpower = spiritualTraits.getTrait(IPluginConstants.WILLPOWER_ID);
    int willpowerValue = willpower.getCreationModel().getValue();
    int virtueSum = new VirtueSumCalculator(spiritualTraits).getSumOfTwoHighestVirtues();
    return (willpowerValue - virtueSum) * 2;
  }
}