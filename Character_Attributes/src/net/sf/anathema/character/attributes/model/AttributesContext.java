package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.core.type.ICharacterType;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public class AttributesContext implements ITraitCollectionContext {

  public static AttributesContext create(ICharacterId characterId, IModelProvider modelProvider) {
    return new AttributesContext(modelProvider, characterId);
  }

  private final ICharacterId characterId;
  private final IModelProvider modelProvider;
  private final AttributeTemplate template = new AttributeTemplate();

  public AttributesContext(IModelProvider modelProvider, ICharacterId characterId) {
    this.modelProvider = modelProvider;
    this.characterId = characterId;
  }

  @Override
  public ITraitTemplate getTraitTemplate() {
    return template.getTraitTemplate();
  }

  @Override
  public IExperience getExperience() {
    return (IExperience) getModel(IExperience.MODEL_ID);
  }

  private Object getModel(String modelId) {
    return modelProvider.getModel(new ModelIdentifier(characterId, modelId));
  }

  @Override
  public ITraitCollectionModel getCollection() {
    return (ITraitCollectionModel) getModel(Attributes.MODEL_ID);
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    return template.getGroups();
  }

  @Override
  public String getActiveImageId() {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    return characterType.getTraitImageId();
  }
}