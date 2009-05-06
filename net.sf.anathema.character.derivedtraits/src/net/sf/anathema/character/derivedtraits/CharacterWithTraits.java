package net.sf.anathema.character.derivedtraits;

import net.sf.anathema.character.abilities.util.AbilitiesDisplayGroupFactory;
import net.sf.anathema.character.attributes.util.AttributeDisplayGroupFactory;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;

public class CharacterWithTraits {

  private final DisplayTraitList<IDisplayTrait> abilities;
  private final DisplayTraitList<IDisplayTrait> attributes;
  private final DisplayTraitList<IDisplayTrait> spiritual;

  public CharacterWithTraits(ICharacter character) {
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

  public IDisplayTrait getSpiritualTrait(String traitName) {
    return spiritual.getTrait(traitName);
  }
}