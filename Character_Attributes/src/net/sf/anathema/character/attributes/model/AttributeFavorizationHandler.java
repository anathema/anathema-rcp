package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeFavorizationHandler extends AbstractTraitCollectionFavorizationHandler {

  private final ICharacterId characterId;
  private final IModelProvider modelProvider;

  public AttributeFavorizationHandler(ICharacterId characterId, IModelProvider modelProvider) {
    this.characterId = characterId;
    this.modelProvider = modelProvider;
  }

  @Override
  protected int getFavoredCount() {
    // TODO Lunars wieder favorisierbar machen
    return 0;
  }

  @Override
  protected ITraitCollectionModel getTraitCollectionModel() {
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, Attributes.MODEL_ID);
    return (ITraitCollectionModel) modelProvider.getModel(modelIdentifier);
  }

}