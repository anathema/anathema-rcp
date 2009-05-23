package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.fake.DummyCharacter;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.validator.where.ValidationDto;
import net.sf.anathema.lib.util.Identificate;

public class ValidationDtoObjectMother {

  public static ValidationDto ForCharacterType(String characterType) {
    ValidationDto dto = new ValidationDto();
    dto.charactertype = characterType;
    return dto;
  }

  public static ValidationDto ForTrait(String traitId) {
    ValidationDto dto = new ValidationDto();
    dto.trait = new BasicTrait(new Identificate(traitId));
    return dto;
  }

  public static ValidationDto ForModel(String modelId, IModel model) {
    ValidationDto validationDto = new ValidationDto();
    validationDto.modelId = modelId;
    DummyCharacter character = new DummyCharacter();
    character.modelsById.put(validationDto.modelId, model);
    validationDto.container = character;
    return validationDto;
  }
}