package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class AttributeGroupTemplate implements ITraitGroupTemplate {

  private static final TraitGroup[] groups = new TraitGroup[] {
        new TraitGroup(Messages.AttributeGroup_PhysicalLabel, "Physical", "Strength", "Dexterity", "Stamina"),  //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        new TraitGroup(Messages.AttributeGroup_SocialLabel, "Social", "Charisma", "Manipulation", "Appearance"),  //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        new TraitGroup(Messages.AttributeGroup_MentalLabel, "Mental", "Perception", "Intelligence", "Wits") }; //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

  public TraitGroup[] getGroups() {
    return groups;
  }
}