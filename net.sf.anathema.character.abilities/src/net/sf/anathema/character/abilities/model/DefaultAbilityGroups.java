package net.sf.anathema.character.abilities.model;

import static net.sf.anathema.character.abilities.util.IAbilityIds.*;
import net.sf.anathema.character.trait.group.TraitGroup;

public class DefaultAbilityGroups {

  private static final TraitGroup WAR_GROUP = new TraitGroup(
      WAR,
      Messages.DefaultAbilityGroups_War,
      ARCHERY,
      ATHLETICS,
      AWARENESS,
      DODGE,
      INTEGRITY,
      MARTIAL_ARTS,
      MELEE,
      RESISTANCE,
      THROWN,
      WAR);
  private static final TraitGroup LIFE_GROUP = new TraitGroup("Life", //$NON-NLS-1$
      Messages.DefaultAbilityGroups_Life,
      CRAFT,
      LARCENY,
      LINGUISTICS,
      PERFORMANCE,
      PRESENCE,
      RIDE,
      SAIL,
      SOCIALIZE,
      STEALTH,
      SURVIVAL);
  private static final TraitGroup WISDOM_GROUP = new TraitGroup("Wisdom", //$NON-NLS-1$
      Messages.DefaultAbilityGroups_Wisdom,
      BUREAUCRACY,
      INVESTIGATION,
      LORE,
      MEDICINE,
      OCCULT);
  private static final TraitGroup[] groups = new TraitGroup[] { WAR_GROUP, LIFE_GROUP, WISDOM_GROUP };

  public TraitGroup[] get() {
    return groups;
  }
}