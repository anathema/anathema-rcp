package net.sf.anathema.character.trait.collection;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.ValidationUtilities;
import net.sf.anathema.character.trait.validator.ValidatorFactory;
import net.sf.anathema.character.trait.validator.where.ValidationDto;
import net.sf.anathema.lib.ui.IUpdatable;

public final class TraitCollectionUpdatable implements IUpdatable {

  private final ValidatorFactory validatorFactory = new ValidatorFactory();
  private final ICharacterTemplate template;
  private final IModelIdentifier identifier;
  private final ITraitCollectionModel model;
  private final IModelContainer container;
  private final ICharacterType characterType;

  public TraitCollectionUpdatable(
      ICharacterTemplate template,
      IModelIdentifier identifier,
      ITraitCollectionModel model,
      ICharacterType characterType) {
    this.template = template;
    this.identifier = identifier;
    this.model = model;
    this.characterType = characterType;
    container = new ModelContainer(ModelCache.getInstance(), identifier.getCharacterId());
  }

  @Override
  public void update() {
    ModelIdentifier experienceIdentifier = new ModelIdentifier(identifier.getCharacterId(), IExperience.MODEL_ID);
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(experienceIdentifier);
    if (experience.isExperienced()) {
      return;
    }
    for (IBasicTrait trait : model.getAllTraits()) {
      validateCreationValue(trait);
    }
  }

  private void validateCreationValue(IBasicTrait trait) {
    IIntValueModel creationModel = trait.getCreationModel();
    int validatedValue = calculateValidatedValue(trait, creationModel.getValue());
    creationModel.setValue(validatedValue);
  }

  private int calculateValidatedValue(IBasicTrait trait, int value) {
    ValidationDto validationObject = createDto(trait);
    List<IValidator> validators = validatorFactory.create(validationObject);
    return ValidationUtilities.getCorrectedValue(validators, value);
  }

  public ValidationDto createDto(IBasicTrait trait) {
    ValidationDto validationObject = new ValidationDto();
    validationObject.templateId = template.getId();
    validationObject.container = container;
    validationObject.modelId = identifier.getModelId();
    validationObject.trait = trait;
    validationObject.charactertype = characterType.getId();
    return validationObject;
  }

}