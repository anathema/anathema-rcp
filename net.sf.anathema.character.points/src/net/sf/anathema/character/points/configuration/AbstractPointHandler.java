package net.sf.anathema.character.points.configuration;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;

public abstract class AbstractPointHandler<M extends IModel> extends UnconfiguredExecutableExtension implements
    IPointHandler {

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
    return calculatePoints(new ModelIdentifier(characterId, modelId));
  }

  @SuppressWarnings("unchecked")
  private int calculatePoints(ModelIdentifier identifier) {
    M model = (M) modelCollection.getModel(identifier);
    return calculatePoints(model, identifier.getCharacterId());
  }

  protected abstract int calculatePoints(M model, ICharacterId characterId);
}