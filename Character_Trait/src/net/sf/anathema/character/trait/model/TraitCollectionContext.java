package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTypeProvider;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.core.type.CharacterTypeProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;

public class TraitCollectionContext implements ITraitCollectionContext, IModelContainer {

  public static TraitCollectionContext create(
      ICharacterId characterId,
      IModelCollection modelProvider,
      String modelId,
      ITraitGroupTemplate groups,
      IMinimalValueFactory templateFactory) {
    IModelContainer modelContainer = new ModelContainer(modelProvider, characterId);
    ICharacterTypeProvider provider = new CharacterTypeProvider(characterId, new CharacterTypeFinder());
    return new TraitCollectionContext(modelContainer, provider, modelId, groups, templateFactory);
  }

  private final ITraitGroupTemplate groups;
  private final IModelContainer modelContainer;
  private final ICharacterTypeProvider characterTypeProvider;
  private final String collectionModelId;
  private final IMinimalValueFactory templateFactory;

  public TraitCollectionContext(
      IModelContainer modelContainer,
      ICharacterTypeProvider characterTypeProvider,
      String collectionModelId,
      ITraitGroupTemplate groups,
      IMinimalValueFactory templateFactory) {
    this.modelContainer = modelContainer;
    this.characterTypeProvider = characterTypeProvider;
    this.collectionModelId = collectionModelId;
    this.groups = groups;
    this.templateFactory = templateFactory;
  }

  @Override
  public int getMinimumValue(String traitId) {
    return templateFactory.getMinimalValue(traitId);
  }

  @Override
  public IModelContainer getModelContainer() {
    return this;
  }

  public IModel getModel(String modelId) {
    return modelContainer.getModel(modelId);
  }

  @Override
  public ITraitCollectionModel getCollection() {
    return (ITraitCollectionModel) getModel(collectionModelId);
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    return groups.getGroups();
  }

  @Override
  public String getActiveImageId() {
    return characterTypeProvider.getCharacterType().getTraitImageId();
  }
}