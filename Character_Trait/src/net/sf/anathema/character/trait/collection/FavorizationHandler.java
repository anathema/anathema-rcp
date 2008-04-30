package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.IIdentificate;

public class FavorizationHandler implements IFavorizationHandler {

  private final ITraitCollectionTemplate template;
  private final IModelContainer modelContainer;
  private final String modelId;

  public FavorizationHandler(
      ICharacterId characterId,
      ITraitCollectionTemplate template,
      IModelCollection modelProvider,
      String modelId) {
    this(new ModelContainer(modelProvider, characterId), template, modelId);
  }

  public FavorizationHandler(IModelContainer modelContainer, ITraitCollectionTemplate template, String modelId) {
    this.modelContainer = modelContainer;
    this.template = template;
    this.modelId = modelId;
  }

  private int getFavoredCount() {
    return template.getFavorizationCount();
  }

  private ITraitCollectionModel getTraitCollectionModel() {
    return (ITraitCollectionModel) modelContainer.getModel(modelId);
  }

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