package net.sf.anathema.character.abilities.points;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.abilities.util.TraitListFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AbilitiesBonusPointHandler extends AbstractPointHandler {

  public AbilitiesBonusPointHandler() {
    super(IAbilitiesPluginConstants.MODEL_ID);
  }

  public AbilitiesBonusPointHandler(IModelCollection modelCollection) {
    super(modelCollection, IAbilitiesPluginConstants.MODEL_ID);
  }

  @Override
  public int calculatePoints(ITraitCollectionModel abilities, ICharacterId characterId) {
    AbilityBonusPointExpenditureBuilder calculation = new AbilityBonusPointExpenditureBuilder();
    for (IBasicTrait trait : new TraitListFactory().createFrom(abilities)) {
      calculation.addTrait(trait);
    }
    return calculation.getCost();
  }
}