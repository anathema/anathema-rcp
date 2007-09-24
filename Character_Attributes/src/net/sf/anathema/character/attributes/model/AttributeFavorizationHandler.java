package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.character.ModelContainer;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeFavorizationHandler extends AbstractTraitCollectionFavorizationHandler {

  private final AttributeTemplate template;
  private final IModelContainer modelContainer;

  public AttributeFavorizationHandler(ICharacterId characterId, AttributeTemplate template, IModelProvider modelProvider) {
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