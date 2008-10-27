package net.sf.anathema.character.abilities.points;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.abilities.util.TraitListFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AbilitiesBonusPointHandler extends AbstractPointHandler {

  private static class PointCalculation {
    int cheapCount = 0;
    int expensiveCount = 0;

    public void addTrait(IBasicTrait trait) {
      if (trait.getStatusManager().getStatus().isCheap()) {
        cheapCount += trait.getCreationModel().getValue();
      }
      else {
        expensiveCount += trait.getCreationModel().getValue();
      }
    }

    public int getTotal() {
      return cheapCount + expensiveCount * 2;
    }
  }

  public AbilitiesBonusPointHandler() {
    super(IAbilitiesPluginConstants.MODEL_ID);
  }

  public AbilitiesBonusPointHandler(IModelCollection modelCollection, IModelResourceHandler resourceHandler) {
    super(modelCollection, IAbilitiesPluginConstants.MODEL_ID);
  }

  @Override
  public int calculatePoints(ITraitCollectionModel abilities, ICharacterId characterId) {
    PointCalculation calculation = new PointCalculation();
    for (IBasicTrait trait : new TraitListFactory().create(abilities)) {
      calculation.addTrait(trait);
    }
    return calculation.getTotal();
  }
}