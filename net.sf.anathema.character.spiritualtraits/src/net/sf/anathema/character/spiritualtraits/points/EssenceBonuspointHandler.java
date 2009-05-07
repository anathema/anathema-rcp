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

  public static PointwiseCostDto getBonusPointCostDto(ICharacterId characterId) {
    IEssenceCostMap costMap = new SpiritualTraitCostExtensionPoint();
    CharacterTypeFinder characterTypeFinder = new CharacterTypeFinder();
    String characterType = characterTypeFinder.getCharacterType(characterId).getId();
    return new EssenceCostDtoFactory(costMap).createCost(characterType);
  }

  public EssenceBonuspointHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel spiritualTraits, ICharacterId characterId) {
    final PointwiseCostDto costDto = getBonusPointCostDto(characterId);
    return new EssenceBonuspointCalculator(spiritualTraits, costDto).calculate();
  }
}