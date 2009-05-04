package net.sf.anathema.character.sheet.socialcombat.attack;

import net.sf.anathema.character.core.character.ICharacter;

public class PerformanceSocialAttack extends AbstractSocialAttack {

  public PerformanceSocialAttack(ICharacter character) {
    super(character);
  }

  public int getRate() {
    return 1;
  }

  public int getSpeed() {
    return 6;
  }

  @Override
  public String getName() {
    return "Performance";
  }
}