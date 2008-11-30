package net.sf.anathema.charms.character;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.charms.character.model.ICharmModel;

public class CharmModelUtilities {

  public static ICharmModel getModelFor(ICharacterId characterId, IModelCollection collection) {
    ModelIdentifier charmIdentifier = new ModelIdentifier(characterId, ICharmModel.MODEL_ID);
    return (ICharmModel) collection.getModel(charmIdentifier);
  }
}