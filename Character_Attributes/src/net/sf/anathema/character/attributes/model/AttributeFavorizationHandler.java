package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeFavorizationHandler extends AbstractTraitCollectionFavorizationHandler {

  private final AttributeTemplate template;
  private final IModelContainer modelContainer;

  public AttributeFavorizationHandler(ICharacterId characterId, AttributeTemplate template, IModelCollection modelProvider) {
    this(new ModelContainer(modelProvider, characterId), template);
  }

  public AttributeFavorizationHandler(IModelContainer modelContainer, AttributeTemplate template) {
    this.modelContainer = modelContainer;
    this.template = template;
  }

  @Override
  protected int getFavoredCount() {
    return template.getFavorizationCount();
  }

  @Override
  protected ITraitCollectionModel getTraitCollectionModel() {
    return (ITraitCollectionModel) modelContainer.getModel(Attributes.MODEL_ID);
  }
}