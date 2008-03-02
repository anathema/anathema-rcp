package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.trait.collection.AbstractTraitCollectionFavorizationHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class FavorizationHandler extends AbstractTraitCollectionFavorizationHandler {

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

  @Override
  protected int getFavoredCount() {
    return template.getFavorizationCount();
  }

  @Override
  protected ITraitCollectionModel getTraitCollectionModel() {
    return (ITraitCollectionModel) modelContainer.getModel(modelId);
  }
}