package net.sf.anathema.character.sheet.socialcombat.attack;

import static net.sf.anathema.character.derivedtraits.CharacterUtilties.*;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.derivedtraits.SocialCombatCharacter;
import net.sf.anathema.character.sheet.socialcombat.ISocialCombatStats;
import net.sf.anathema.character.trait.display.IDisplayTrait;

public abstract class AbstractSocialAttack implements ISocialCombatStats {

  private final SocialCombatCharacter character;

  public AbstractSocialAttack(ICharacter character) {
    this.character = new SocialCombatCharacter(character);
  }

  public final int getDeceptionAttackValue() {
    IDisplayTrait attribute = getDeceptionAttribute();
    IDisplayTrait ability = getAbility();
    return getTotalValue(attribute, ability);
  }

  public final int getDeceptionMDV() {
    IDisplayTrait deceptionAttribute = getDeceptionAttribute();
    IDisplayTrait ability = getAbility();
    return getRoundUpDv(deceptionAttribute, ability);
  }

  public final int getHonestyAttackValue() {
    IDisplayTrait honestyAttribute = getHonestyAttribute();
    IDisplayTrait ability = getAbility();
    return getTotalValue(honestyAttribute, ability);
  }

  public final int getHonestyMDV() {
    IDisplayTrait honestyAttribute = getHonestyAttribute();
    IDisplayTrait ability = getAbility();
    return getRoundUpDv(honestyAttribute, ability);
  }

  private IDisplayTrait getAbility() {
    return character.getAbility(getName());
  }

  private IDisplayTrait getDeceptionAttribute() {
    return character.getAttribute("Manipulation");
  }

  private IDisplayTrait getHonestyAttribute() {
    return character.getAttribute("Charisma");
  }
}