package net.sf.anathema.character.trait.model;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTypeProvider;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.core.type.CharacterTypeProvider;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.ValidatorFactory;

public class TraitCollectionContext implements ITraitCollectionContext, IModelContainer {

  public static TraitCollectionContext create(
      ICharacterId characterId,
      IModelCollection modelProvider,
      String modelId,
      ITraitGroupTemplate groups) {
    IModelContainer modelContainer = new ModelContainer(modelProvider, characterId);
    ICharacterTypeProvider provider = new CharacterTypeProvider(characterId, new CharacterTypeFinder());
    String templateId = new CharacterTemplateProvider().getTemplate(characterId).getId();
    return new TraitCollectionContext(templateId, modelContainer, provider, modelId, groups);
  }

  private final ITraitGroupTemplate groups;
  private final IModelContainer modelContainer;
  private final ICharacterTypeProvider characterTypeProvider;
  private final String collectionModelId;
  private final String templateId;

  public TraitCollectionContext(
      String templateId,
      IModelContainer modelContainer,
      ICharacterTypeProvider characterTypeProvider,
      String collectionModelId,
      ITraitGroupTemplate groups) {
    this.templateId = templateId;
    this.modelContainer = modelContainer;
    this.characterTypeProvider = characterTypeProvider;
    this.collectionModelId = collectionModelId;
    this.groups = groups;
  }

  @Override
  public List<IValidator> getValidators(String traitId) {
    IBasicTrait trait = getCollection().getTrait(traitId);
    return new ValidatorFactory().create(templateId, getModelContainer(), trait);
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