package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

public class AttributeFavorizationHandler extends AbstractTraitCollectionFavorizationHandler {

  public AttributeFavorizationHandler(
      ICharacterId characterId,
      IModelProvider modelProvider,
      ICreditManager creditManager) {
    super(characterId, modelProvider, creditManager);
  }

  @Override
  protected String getModelId() {
    return Attributes.MODEL_ID;
  }

  @Override
  protected String getCreditId() {
    return "net.sf.anathema.character.attributes.favored"; //$NON-NLS-1$
  }
}