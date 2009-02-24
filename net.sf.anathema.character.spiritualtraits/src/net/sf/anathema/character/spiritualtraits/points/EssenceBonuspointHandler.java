package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.essence.EssenceBonuspointCalculator;
import net.sf.anathema.character.spiritualtraits.points.essence.EssenceCostDtoFactory;
import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.PointwiseCostDto;

public class EssenceBonuspointHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public EssenceBonuspointHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel spiritualTraits, ICharacterId characterId) {
    IEssenceCostMap costMap = new SpiritualTraitCostExtensionPoint();
    CharacterTypeFinder characterTypeFinder = new CharacterTypeFinder();
    String characterType = characterTypeFinder.getCharacterType(characterId).getId();
    final PointwiseCostDto costDto = new EssenceCostDtoFactory(costMap).createCost(characterType);
    return new EssenceBonuspointCalculator(spiritualTraits, costDto).calculate();
  }
}