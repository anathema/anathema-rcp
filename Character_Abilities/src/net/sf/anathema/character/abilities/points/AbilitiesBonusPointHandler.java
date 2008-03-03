package net.sf.anathema.character.abilities.points;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AbilitiesBonusPointHandler extends AbstractPointHandler {
  private static final String HANDLER_TYPE = "abilities"; //$NON-NLS-1$

  public AbilitiesBonusPointHandler() {
    super(HANDLER_TYPE, IAbilitiesPluginConstants.MODEL_ID);
  }

  public AbilitiesBonusPointHandler(IModelCollection modelCollection, IModelResourceHandler resourceHandler) {
    super(modelCollection, resourceHandler, HANDLER_TYPE, IAbilitiesPluginConstants.MODEL_ID);
  }

  @Override
  public int calculatePoints(ITraitCollectionModel abilities, ICharacterId characterId) {
    int cheapCount = 0;
    int expensiveCount = 0;
    for (IBasicTrait trait : abilities.getTraits()) {
      if (trait.getStatusManager().getStatus().isCheap()) {
        cheapCount += trait.getCreationModel().getValue();
      }
      else {
        expensiveCount += trait.getCreationModel().getValue();
      }
    }
    return cheapCount + expensiveCount * 2;
  }
}