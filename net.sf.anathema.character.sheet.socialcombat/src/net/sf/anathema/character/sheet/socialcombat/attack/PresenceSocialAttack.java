package net.sf.anathema.character.sheet.socialcombat.attack;

import net.sf.anathema.character.core.character.ICharacter;

public class PresenceSocialAttack extends AbstractSocialAttack {

  public PresenceSocialAttack(ICharacter character) {
    super(character);
  }

  public int getRate() {
    return 2;
  }

  public int getSpeed() {
    return 4;
  }

  @Override
  public String getName() {
    return "Presence";
  }
}