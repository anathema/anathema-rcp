package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.internal.FavoredCount;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.IIdentificate;

public class FavorizationInteraction implements IFavorizationInteraction {

  private final IFavorizationTemplate template;
  private final IModelContainer modelContainer;
  private final String modelId;

  public FavorizationInteraction(IModelContainer modelContainer, IFavorizationTemplate template, String modelId) {
    this.modelContainer = modelContainer;
    this.template = template;
    this.modelId = modelId;
  }

  private ITraitCollectionModel getTraitCollectionModel() {
    return (ITraitCollectionModel) modelContainer.getModel(modelId);
  }

  @Override
  public boolean isFavorable() {
    return template.getAllowedFavored() > 0;
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
    if (FavoredStatus.isFavored(trait)) {
      return !template.isRequiredFavored(trait.getTraitType());
    }
    return new FavoredCount(traitCollection).get() < template.getAllowedFavored();
  }
}