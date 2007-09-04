package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public class AttributeCharacterContext implements IAttributeCharacterContext {

  private final ICharacterId characterId;
  private final IModelProvider modelProvider;
  private final AttributeTemplate template = new AttributeTemplate();
  private final IFavorizationHandler favorizationHandler;

  public AttributeCharacterContext(
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
  public IAttributes getAttributes() {
    return (IAttributes) getModel(IAttributes.MODEL_ID);
  }

  @Override
  public IFavorizationHandler getFavorizationHandler() {
    return favorizationHandler;
  }
  
  @Override
  public TraitGroup[] getTraitGroups() {
    return template.getGroups();
  }
}