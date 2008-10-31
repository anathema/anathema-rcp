package net.sf.anathema.character.freebies.configuration;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.status.FavoredStatus;

public abstract class AbstractFavoredTraitCountHandler extends AbstractExecutableExtension implements IFreebiesHandler {

  private final IModelCollection modelProvider;

  public AbstractFavoredTraitCountHandler(IModelCollection modelCollection) {
    modelProvider = modelCollection;
  }

  @Override
  public final int getPoints(ICharacterId id, int credit) {
    IModelIdentifier modelIdentifer = new ModelIdentifier(id, getModelId());
    ITraitCollectionModel traitCollection = (ITraitCollectionModel) modelProvider.getModel(modelIdentifer);
    int count = 0;
    for (IBasicTrait trait : traitCollection.getTraits()) {
      if (trait.getStatusManager().getStatus() instanceof FavoredStatus) {
        count++;
      }
    }
    return count;
  }

  protected abstract String getModelId();
}