package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTypeProvider;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.core.type.CharacterTypeProvider;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class TraitCollectionContext implements ITraitCollectionContext {

  public static TraitCollectionContext create(
      ICharacterId characterId,
      IModelCollection modelProvider,
      String modelId,
      ITraitGroupConfiguration groups) {
    IModelContainer modelContainer = new ModelContainer(modelProvider, characterId);
    ICharacterTypeProvider provider = new CharacterTypeProvider(characterId, new CharacterTypeFinder());
    return new TraitCollectionContext(modelContainer, provider, modelId, groups);
  }

  private final ITraitGroupConfiguration groups;
  private final IModelContainer modelContainer;
  private final ICharacterTypeProvider characterTypeProvider;
  private final String collectionModelId;

  public TraitCollectionContext(
      IModelContainer modelContainer,
      ICharacterTypeProvider characterTypeProvider,
      String collectionModelId,
      ITraitGroupConfiguration groups) {
    this.modelContainer = modelContainer;
    this.characterTypeProvider = characterTypeProvider;
    this.collectionModelId = collectionModelId;
    this.groups = groups;
  }

  @Override
  public ITraitTemplate getTraitTemplate() {
    return groups.getTraitTemplate();
  }

  @Override
  public IExperience getExperience() {
    return (IExperience) getModel(IExperience.MODEL_ID);
  }

  private Object getModel(String modelId) {
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