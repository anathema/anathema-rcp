package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.essence.CharacterExperienceCost;
import net.sf.anathema.character.spiritualtraits.points.essence.EssenceExperienceCalculatoryFactory;
import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.lang.ICalculator;

public class EssenceExperiencePointHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public EssenceExperiencePointHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(final ITraitCollectionModel traits, final ICharacterId characterId) {
    final IEssenceCostMap costMap = new SpiritualTraitCostExtensionPoint();
    final int xpCost = new CharacterExperienceCost(characterId, costMap).getXpCost();
    final EssenceExperienceCalculatoryFactory factory = new EssenceExperienceCalculatoryFactory(traits, xpCost);
    final ICalculator calculator = factory.create();
    return calculator.calculate();
  }
}