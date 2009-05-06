package net.sf.anathema.character.derivedtraits;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.trait.display.IDisplayTrait;

public class CombatCharacter {

  private final ICharacter character;
  private final CharacterWithTraits characterWithTraits;

  public CombatCharacter(ICharacter character) {
    this.character = character;
    this.characterWithTraits = new CharacterWithTraits(character);
  }

  public int getAttackValue(AttackDto attackDto) {
    IDisplayTrait attribute = characterWithTraits.getAttribute(attackDto.attribute);
    IDisplayTrait ability = characterWithTraits.getAbility(attackDto.ability);
    return attribute.getValue() + ability.getValue() + attackDto.bonus;
  }

  public int getDefenceValue(AttackDto attackDto) {
    double attackValue = getAttackValue(attackDto);
    double parryBase = attackValue / 2;
    return roundParryValue(parryBase);
  }

  private int roundParryValue(double base) {
    if (hasLowParryDefence()) {
      return (int) Math.floor(base);
    }
    return (int) Math.ceil(base);
  }

  private boolean hasLowParryDefence() {
    ICharacterType characterType = character.getCharacterType();
    return new HasLowParryValue().evaluate(characterType);
  }

  public int getDamage(DamageDto dto) {
    if (dto.attribute == null) {
      return dto.bonus;
    }
    IDisplayTrait attribute = characterWithTraits.getAttribute(dto.attribute);
    return attribute.getValue() + dto.bonus;
  }
}