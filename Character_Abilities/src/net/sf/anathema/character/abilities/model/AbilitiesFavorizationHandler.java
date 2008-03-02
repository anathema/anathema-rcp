package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.abilities.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.trait.collection.AbstractTraitCollectionFavorizationHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AbilitiesFavorizationHandler extends AbstractTraitCollectionFavorizationHandler {

  private final ITraitCollectionTemplate template;
  private final IModelContainer modelContainer;

  public AbilitiesFavorizationHandler(ICharacterId characterId, ITraitCollectionTemplate template, IModelCollection modelProvider) {
    this(new ModelContainer(modelProvider, characterId), template);
  }

  public AbilitiesFavorizationHandler(IModelContainer modelContainer, ITraitCollectionTemplate template) {
    this.modelContainer = modelContainer;
    this.template = template;
  }

  @Override
  protected int getFavoredCount() {
    return template.getFavorizationCount();
  }

  @Override
  protected ITraitCollectionModel getTraitCollectionModel() {
    return (ITraitCollectionModel) modelContainer.getModel(IAbilitiesPluginConstants.MODEL_ID);
  }
}