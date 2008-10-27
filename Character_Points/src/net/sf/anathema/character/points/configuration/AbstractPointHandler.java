package net.sf.anathema.character.points.configuration;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public abstract class AbstractPointHandler extends AbstractExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;
  private final String modelId;

  public AbstractPointHandler(String modelId) {
    this(ModelCache.getInstance(), modelId);
  }

  public AbstractPointHandler(IModelCollection modelCollection, String modelId) {
    this.modelCollection = modelCollection;
    this.modelId = modelId;
  }

  @Override
  public final int getPoints(ICharacterId characterId) {
    if (characterId == null) {
      return 0;
    }
    return calculatePoints(new ModelIdentifier(characterId, modelId));
  }

  private int calculatePoints(ModelIdentifier identifier) {
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelCollection.getModel(identifier);
    return calculatePoints(attributes, identifier.getCharacterId());
  }

  protected abstract int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId);
}