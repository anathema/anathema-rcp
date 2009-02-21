package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class ValidationDtoObjectMother {

  public static ValidationDto ForCharacterType(String characterType) {
    ValidationDto dto = new ValidationDto();
    dto.charactertype = characterType;
    return dto;
  }

}
