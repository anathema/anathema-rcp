package net.sf.anathema.character.derivedtraits;

import static net.sf.anathema.character.abilities.util.IAbilityIds.*;
import static net.sf.anathema.character.attributes.model.IAttributeIds.*;
import static net.sf.anathema.character.derivedtraits.CharacterUtilties.*;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.IDisplayTrait;

public class SocialCombatCharacter extends CharacterWithTraits {

  public SocialCombatCharacter(ICharacter character) {
    super(character);
  }

  public int getJoinDebate() {
    IDisplayTrait wits = getAttribute(WITS);
    IDisplayTrait awareness = getAbility(AWARENESS);
    return getTotalValue(wits, awareness);
  }

  public int getDodgeMdv() {
    IDisplayTrait integrity = getAbility(INTEGRITY);
    IDisplayTrait essence = getSpiritualTrait(IPluginConstants.ESSENCE_ID);
    IDisplayTrait willpower = getSpiritualTrait(IPluginConstants.WILLPOWER_ID);
    return getRoundDownDv(integrity, essence, willpower);
  }
}