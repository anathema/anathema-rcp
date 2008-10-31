package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.character.caste.trait.ITraitCollectionProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;

public final class TraitCollectionProvider implements ITraitCollectionProvider {
  private final String modelId;
  private final IModelCollection modelCollection;

  public TraitCollectionProvider(IModelCollection modelCollection, String modelId) {
    this.modelCollection = modelCollection;
    this.modelId = modelId;
  }

  @Override
  public ITraitCollectionModel getModel(ICharacterId id) {
    if (modelId == null) {
      return new TraitCollection();
    }
    return (ITraitCollectionModel) modelCollection.getModel(new ModelIdentifier(id, modelId));
  }
}