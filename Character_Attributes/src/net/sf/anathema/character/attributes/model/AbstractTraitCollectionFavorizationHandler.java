package net.sf.anathema.character.attributes.model;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractTraitCollectionFavorizationHandler implements IFavorizationHandler {

  protected abstract int getFavoredCount();

  protected abstract ITraitCollectionModel getTraitCollectionModel();

  @Override
  public boolean isFavorable() {
    return getFavoredCount() > 0;
  }

  @Override
  public void toogleFavored(IIdentificate traitType) {
    if (!isFavorable()) {
      return;
    }
    ITraitCollectionModel attributes = getTraitCollectionModel();
    IBasicTrait trait = attributes.getTrait(traitType.getId());
    BooleanModel favoredModel = trait.getFavoredModel();
    if (isToggleFavoredAllowed(attributes, trait)) {
      favoredModel.setValue(!favoredModel.getValue());
    }
  }

  private boolean isToggleFavoredAllowed(ITraitCollectionModel attributes, IBasicTrait trait) {
    boolean isFavored = trait.getFavoredModel().getValue();
    if (isFavored) {
      return true;
    }
    int favoredCount = 0;
    for (IBasicTrait attribute : attributes.getTraits()) {
      if (attribute.getFavoredModel().getValue()) {
        favoredCount++;
      }
    }
    return favoredCount < getFavoredCount();
  }
}