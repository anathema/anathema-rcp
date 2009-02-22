package net.sf.anathema.character.trait.validator;

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
}