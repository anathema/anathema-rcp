package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.group.TraitGroup;

public class AttributeTemplate {

  private final TraitGroup[] groups = new TraitGroup[] {
      new TraitGroup("Physical", "Strength", "Dexterity", "Stamina"), //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      new TraitGroup("Social", "Charisma", "Manipulation", "Appearance"), //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      new TraitGroup("Mental", "Perception", "Intelligence", "Wits") }; //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

  public TraitGroup[] getGroups() {
    return groups;
  }
}