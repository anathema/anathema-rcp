package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.trait.group.TraitGroup;

public class DefaultAbilityGroups {

  private static final TraitGroup WAR_GROUP = new TraitGroup("War", //$NON-NLS-1$
      Messages.DefaultAbilityGroups_War,
      "Archery", //$NON-NLS-1$
      "Athletics", //$NON-NLS-1$
      "Awareness", //$NON-NLS-1$
      "Dodge", //$NON-NLS-1$
      "Integrity", //$NON-NLS-1$
      "MartialArts", //$NON-NLS-1$
      "Melee", //$NON-NLS-1$
      "Resistance", //$NON-NLS-1$
      "Thrown", "War"); //$NON-NLS-1$ //$NON-NLS-2$
  private static final TraitGroup LIFE_GROUP = new TraitGroup("Life", //$NON-NLS-1$
      Messages.DefaultAbilityGroups_Life,
      "Craft", //$NON-NLS-1$
      "Larceny", //$NON-NLS-1$
      "Linguistics", //$NON-NLS-1$
      "Performance", //$NON-NLS-1$
      "Presence", //$NON-NLS-1$
      "Ride", //$NON-NLS-1$
      "Sail", //$NON-NLS-1$
      "Socialize", //$NON-NLS-1$
      "Stealth", "Survival"); //$NON-NLS-1$ //$NON-NLS-2$
  private static final TraitGroup WISDOM_GROUP = new TraitGroup("Wisdom", //$NON-NLS-1$
      Messages.DefaultAbilityGroups_Wisdom,
      "Bureaucracy", //$NON-NLS-1$
      "Investigation", //$NON-NLS-1$
      "Lore", //$NON-NLS-1$
      "Medicine", //$NON-NLS-1$
      "Occult"); //$NON-NLS-1$
  private static final TraitGroup[] groups = new TraitGroup[] { WAR_GROUP, LIFE_GROUP, WISDOM_GROUP };

  public TraitGroup[] get() {
    return groups;
  }
}
