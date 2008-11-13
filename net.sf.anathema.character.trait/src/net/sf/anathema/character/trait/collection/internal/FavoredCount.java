package net.sf.anathema.character.trait.collection.internal;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.status.FavoredStatus;

public class FavoredCount {

  private final ITraitCollectionModel traitCollection;

  public FavoredCount(ITraitCollectionModel traitCollection) {
    this.traitCollection = traitCollection;
  }

  public int get() {
    int favoredCount = 0;
    for (IBasicTrait trait : traitCollection.getTraits()) {
      if (FavoredStatus.isFavored(trait)) {
        favoredCount++;
      }
    }
    return favoredCount;
  }
}