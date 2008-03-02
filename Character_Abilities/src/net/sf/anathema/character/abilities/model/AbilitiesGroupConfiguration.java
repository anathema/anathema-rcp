package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class AbilitiesGroupConfiguration {

  public TraitGroup[] getGroups() {
    return new TraitGroup[] { new TraitGroup(
        "Abilities",
        "Archery",
        "Athletics",
        "Awareness",
        "Bureaucracy",
        "Craft",
        "Dodge",
        "Integrity",
        "Investigation",
        "Larceny",
        "Linguistics",
        "Lore",
        "MartialArts",
        "Medicine",
        "Melee",
        "Occult",
        "Performance",
        "Presence",
        "Resistance",
        "Ride",
        "Sail",
        "Socialize",
        "Stealth",
        "Survival",
        "Thrown",
        "War") };
  }

  public ITraitTemplate getTraitTemplate() {
    EssenceSensitiveTraitTemplate traitTemplate = new EssenceSensitiveTraitTemplate();
    traitTemplate.setMiniumalValue(0);
    return traitTemplate;
  }
}