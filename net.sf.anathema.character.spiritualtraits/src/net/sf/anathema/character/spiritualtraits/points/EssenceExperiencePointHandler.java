package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.essence.EssenceExperienceCalculator;
import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class EssenceExperiencePointHandler extends AbstractPointHandler<ITraitCollectionModel> {

  public EssenceExperiencePointHandler() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel spiritualTraits, ICharacterId characterId) {
    IEssenceCostMap costMap = new SpiritualTraitCostExtensionPoint();
    CharacterTypeFinder characterTypeFinder = new CharacterTypeFinder();
    String characterType = characterTypeFinder.getCharacterType(characterId).getId();
    int experienceCost = costMap.getEssenceExperienceCost(characterType);
    return new EssenceExperienceCalculator(spiritualTraits, experienceCost).calculate();
  }
}