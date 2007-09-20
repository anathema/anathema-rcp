package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.core.type.ICharacterType;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public class AttributesContext implements ITraitCollectionContext {

  private final ICharacterId characterId;
  private final IModelProvider modelProvider;
  private final AttributeTemplate template = new AttributeTemplate();
  private final IFavorizationHandler favorizationHandler;

  public AttributesContext(
      IModelProvider modelProvider,
      ICharacterId characterId,
      IFavorizationHandler favorizationHandler) {
    this.modelProvider = modelProvider;
    this.characterId = characterId;
    this.favorizationHandler = favorizationHandler;
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
  public IFavorizationHandler getFavorizationHandler() {
    return favorizationHandler;
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