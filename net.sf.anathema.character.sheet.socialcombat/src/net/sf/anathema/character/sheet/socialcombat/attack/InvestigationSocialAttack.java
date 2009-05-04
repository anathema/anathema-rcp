package net.sf.anathema.character.sheet.socialcombat.attack;

import net.sf.anathema.character.core.character.ICharacter;

public class InvestigationSocialAttack extends AbstractSocialAttack {

  public InvestigationSocialAttack(ICharacter character) {
    super(character);
  }

  public int getRate() {
    return 2;
  }

  public int getSpeed() {
    return 5;
  }

  @Override
  public String getName() {
    return "Investigation";
  }
}