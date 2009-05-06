package net.sf.anathema.character.sheet.health;

import static net.sf.anathema.character.abilities.util.IAbilityIds.*;
import static net.sf.anathema.character.attributes.model.IAttributeIds.*;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.derivedtraits.CharacterWithTraits;
import net.sf.anathema.character.trait.display.IDisplayTrait;

public class HealthyCharacter implements IHealthyCharacter {

  private final CharacterWithTraits character;

  public HealthyCharacter(ICharacter character) {
    this.character = new CharacterWithTraits(character);
  }

  @Override
  public int getMoveValue(HealthLevelType injuryLevel) {
    int penalty = getInjuryPenalty(injuryLevel);
    IDisplayTrait dexterity = character.getAttribute(DEXTERITY);
    int dexValue = dexterity.getValue();
    return dexValue + penalty;
  }

  private int getInjuryPenalty(HealthLevelType injuryLevel) {
    int painTolerance = getPainTolerance();
    return getPenalty(injuryLevel, painTolerance);
  }

  private int getPainTolerance() {
    // TODO Pain Tolerance
    return 0;
  }

  private final int getPenalty(HealthLevelType level, int painTolerance) {
    return Math.min(0, level.getIntValue() + painTolerance);
  }

  @Override
  public int getDashValue(HealthLevelType injuryLevel) {
    return getMoveValue(injuryLevel) + 6;
  }

  @Override
  public int getVerticalJump(HealthLevelType level) {
    int penalty = getInjuryPenalty(level);
    IDisplayTrait strength = character.getAttribute(STRENGTH);
    IDisplayTrait athletics = character.getAbility(ATHLETICS);
    return strength.getValue() + athletics.getValue() + penalty;
  }

  @Override
  public int getDyingLevels() {
    return character.getAttribute(STAMINA).getValue();
  }

  @Override
  public int getHealthLevelCount(HealthLevelType level) {
    return 0;
  }

  @Override
  public boolean hasPainTolerance() {
    return getPainTolerance() > 0;
  }

  @Override
  public int getPenalty(HealthLevelType level) {
    int painTolerance = getPainTolerance();
    return getPenalty(level, painTolerance);
  }
}