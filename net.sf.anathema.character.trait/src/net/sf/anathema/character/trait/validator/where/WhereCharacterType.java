package net.sf.anathema.character.trait.validator.where;

public class WhereCharacterType implements IWhere {

  private final String characterTypeId;

  public WhereCharacterType(String characterTypeId) {
    this.characterTypeId = characterTypeId;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    return characterTypeId.equals(validationObject.charactertype);
  }
}