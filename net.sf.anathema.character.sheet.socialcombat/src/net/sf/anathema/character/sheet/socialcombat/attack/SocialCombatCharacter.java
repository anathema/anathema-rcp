package net.sf.anathema.character.sheet.socialcombat.attack;

import static net.sf.anathema.character.sheet.socialcombat.attack.CharacterUtilties.*;
import net.sf.anathema.character.abilities.util.AbilitiesDisplayGroupFactory;
import net.sf.anathema.character.attributes.util.AttributeDisplayGroupFactory;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;

public class SocialCombatCharacter {

  private final DisplayTraitList<IDisplayTrait> abilities;
  private final DisplayTraitList<IDisplayTrait> attributes;
  private final DisplayTraitList<IDisplayTrait> spiritual;

  public SocialCombatCharacter(ICharacter character) {
    this.abilities = new DisplayTraitList<IDisplayTrait>(
        new AbilitiesDisplayGroupFactory().createDisplayTraitGroups(character));
    this.attributes = new DisplayTraitList<IDisplayTrait>(
        new AttributeDisplayGroupFactory().createDisplayTraitGroups(character));
    this.spiritual = new DisplayTraitList<IDisplayTrait>(
        new SpiritualTraitDisplayGroupFactory().createDisplayTraitGroups(character));
  }

  public IDisplayTrait getAttribute(String traitName) {
    return attributes.getTrait(traitName);
  }

  public IDisplayTrait getAbility(String traitName) {
    return abilities.getTrait(traitName);
  }

  private IDisplayTrait getSpiritualTrait(String traitName) {
    return spiritual.getTrait(traitName);
  }

  public int getJoinDebate() {
    IDisplayTrait wits = getAttribute("Wits");
    IDisplayTrait awareness = getAbility("Awareness");
    return getTotalValue(wits, awareness);
  }

  public int getDodgeMdv() {
    IDisplayTrait integrity = getAbility("Integrity");
    IDisplayTrait essence = getSpiritualTrait(IPluginConstants.ESSENCE_ID);
    IDisplayTrait willpower = getSpiritualTrait(IPluginConstants.WILLPOWER_ID);
    return getRoundDownDv(integrity, essence, willpower);
  }
}