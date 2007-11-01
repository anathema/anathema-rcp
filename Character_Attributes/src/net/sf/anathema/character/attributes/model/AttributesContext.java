package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
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

public class AttributesContext implements ITraitCollectionContext {

  public static AttributesContext create(ICharacterId characterId, IModelCollection modelProvider) {
    IModelContainer modelContainer = new ModelContainer(modelProvider, characterId);
    ICharacterTypeProvider provider = new CharacterTypeProvider(characterId, new CharacterTypeFinder());
    return new AttributesContext(modelContainer, provider);
  }

  private final AttributeGroupConfiguration groups = new AttributeGroupConfiguration();
  private final IModelContainer modelContainer;
  private final ICharacterTypeProvider characterTypeProvider;

  public AttributesContext(IModelContainer modelContainer, ICharacterTypeProvider characterTypeProvider) {
    this.modelContainer = modelContainer;
    this.characterTypeProvider = characterTypeProvider;
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
    return (ITraitCollectionModel) getModel(Attributes.MODEL_ID);
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    return groups.getGroups();
  }

  @Override
  public String getActiveImageId() {
    ICharacterType characterType = characterTypeProvider.getCharacterType();
    return characterType.getTraitImageId();
  }
}