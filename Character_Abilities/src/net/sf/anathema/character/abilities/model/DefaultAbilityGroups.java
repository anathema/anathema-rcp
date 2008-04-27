package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.trait.group.TraitGroup;

public class DefaultAbilityGroups {

  private static final TraitGroup[] groups = new TraitGroup[] { new TraitGroup("War", //$NON-NLS-1$
      Messages.DefaultAbilityGroups_War,
      "Archery", //$NON-NLS-1$
      "Athletics", //$NON-NLS-1$
      "Awareness", //$NON-NLS-1$
      "Dodge", //$NON-NLS-1$
      "Integrity", //$NON-NLS-1$
      "MartialArts", //$NON-NLS-1$
      "Melee", //$NON-NLS-1$
      "Resistance", //$NON-NLS-1$
      "Thrown", "War"), //$NON-NLS-1$ //$NON-NLS-2$
      new TraitGroup("Life", //$NON-NLS-1$
          Messages.DefaultAbilityGroups_Life,
          "Craft", //$NON-NLS-1$
          "Larceny", //$NON-NLS-1$
          "Linguistics", //$NON-NLS-1$
          "Performance", //$NON-NLS-1$
          "Presence", //$NON-NLS-1$
          "Ride", //$NON-NLS-1$
          "Sail", //$NON-NLS-1$
          "Socialize", //$NON-NLS-1$
          "Stealth", "Survival"), //$NON-NLS-1$ //$NON-NLS-2$
      new TraitGroup(
          "Wisdom", Messages.DefaultAbilityGroups_Wisdom, "Bureaucracy", "Investigation", "Lore", "Medicine", "Occult") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

  public TraitGroup[] get() {
    return groups;
  }
}
