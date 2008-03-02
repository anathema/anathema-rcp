package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractTraitCollectionFavorizationHandler implements IFavorizationHandler {

  protected abstract int getFavoredCount();

  protected abstract ITraitCollectionModel getTraitCollectionModel();

  @Override
  public boolean isFavorable() {
    return getFavoredCount() > 0;
  }

  @Override
  public void toggleFavored(IIdentificate traitType) {
    if (!isFavorable()) {
      return;
    }
    ITraitCollectionModel traitCollection = getTraitCollectionModel();
    IBasicTrait trait = traitCollection.getTrait(traitType.getId());
    if (isToggleFavoredAllowed(traitCollection, trait)) {
      trait.getStatusManager().toggleStatus();
    }
  }

  private boolean isToggleFavoredAllowed(ITraitCollectionModel traitCollection, IBasicTrait trait) {
    if (isFavored(trait)) {
      return true;
    }
    int favoredCount = 0;
    for (IBasicTrait collectionTrait : traitCollection.getTraits()) {
      if (isFavored(collectionTrait)) {
        favoredCount++;
      }
    }
    return favoredCount < getFavoredCount();
  }

  private boolean isFavored(IBasicTrait trait) {
    return trait.getStatusManager().getStatus() instanceof FavoredStatus;
  }
}