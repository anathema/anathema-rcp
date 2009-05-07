package net.sf.anathema.character.derivedtraits;

import static net.sf.anathema.character.abilities.util.IAbilityIds.*;
import static net.sf.anathema.character.attributes.model.IAttributeIds.*;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.trait.display.IDisplayTrait;

public class CombatCharacter implements ICombatCharacter {

  private final CharacterWithTraits characterWithTraits;
  private final ICharacter character;

  public CombatCharacter(ICharacter character) {
    this.character = character;
    this.characterWithTraits = new CharacterWithTraits(character);
  }

  @Override
  public int getJoinBattle() {
    IDisplayTrait wits = characterWithTraits.getAttribute(WITS);
    IDisplayTrait awareness = characterWithTraits.getAbility(AWARENESS);
    return wits.getValue() + awareness.getValue();
  }

  @Override
  public int getDodgeDv() {
    ICharacterType characterType = character.getCharacterType();
    IDisplayTrait[] relevantTraits = getRelevantDodgeTraits();
    if (new HasLowDodgeDV().evaluate(characterType)) {
      return CharacterUtilties.getRoundDownDv(relevantTraits);
    }
    return CharacterUtilties.getRoundUpDv(relevantTraits);
  }

  private IDisplayTrait[] getRelevantDodgeTraits() {
    IDisplayTrait essence = characterWithTraits.getEssence();
    IDisplayTrait dexterity = characterWithTraits.getDexterity();
    IDisplayTrait dodge = characterWithTraits.getDodge();
    if (essence.getValue() > 1) {
      return new IDisplayTrait[] { dexterity, dodge, essence };
    }
    return new IDisplayTrait[] { dexterity, dodge };
  }

  @Override
  public int getKnockdownThreshold() {
    IDisplayTrait stamina = characterWithTraits.getStamina();
    IDisplayTrait resistance = characterWithTraits.getResistance();
    return stamina.getValue() + resistance.getValue();
  }

  @Override
  public int getKnockdownPool() {
    int attributeValue = getMaxValue(characterWithTraits.getDexterity(), characterWithTraits.getStamina());
    int abilityValue = getMaxValue(characterWithTraits.getAthletics(), characterWithTraits.getResistance());
    return attributeValue + abilityValue;
  }

  private int getMaxValue(IDisplayTrait first, IDisplayTrait second) {
    return Math.max(first.getValue(), second.getValue());
  }

  @Override
  public int getStunningThreshold() {
    return characterWithTraits.getStamina().getValue();
  }

  @Override
  public int getStunningPool() {
    return characterWithTraits.getStamina().getValue() + characterWithTraits.getResistance().getValue();
  }
}