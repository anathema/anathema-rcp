package net.sf.anathema.character.trait.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.ValidatorFactory;

public class TraitCollectionContext implements ITraitCollectionContext {

  public static TraitCollectionContext create(ICharacter character, String modelId, ITraitGroupTemplate groups) {
    return new TraitCollectionContext(character, modelId, groups);
  }

  private final ITraitGroupTemplate groupTemplate;
  private final ICharacter character;
  private final String modelId;

  public TraitCollectionContext(ICharacter character, String modelId, ITraitGroupTemplate groupTemplate) {
    this.character = character;
    this.modelId = modelId;
    this.groupTemplate = groupTemplate;
  }

  @Override
  public List<IValidator> getValidators(String traitId) {
    if (!getCollection().contains(traitId)) {
      return new ArrayList<IValidator>();
    }
    IBasicTrait trait = getCollection().getTrait(traitId);
    String templateId = character.getTemplate().getId();
    return new ValidatorFactory().create(templateId, character, modelId, trait);
  }

  @Override
  public IExperience getExperience() {
    return (IExperience) character.getModel(IExperience.MODEL_ID);
  }

  @Override
  public ITraitCollectionModel getCollection() {
    return (ITraitCollectionModel) character.getModel(modelId);
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    return groupTemplate.getGroups();
  }

  @Override
  public String getActiveImageId() {
    return character.getCharacterType().getTraitImageId();
  }
}