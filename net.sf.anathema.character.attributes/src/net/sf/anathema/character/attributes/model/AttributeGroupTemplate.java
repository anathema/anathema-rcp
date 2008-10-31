package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class AttributeGroupTemplate implements ITraitGroupTemplate {

  private static final TraitGroup[] groups = new TraitGroup[] {
      new TraitGroup(
          Messages.AttributeGroup_PhysicalLabel,
          Messages.AttributeGroupTemplate_PhysicalGroup,
          "Strength", "Dexterity", "Stamina"),  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
      new TraitGroup(
          Messages.AttributeGroup_SocialLabel,
          Messages.AttributeGroupTemplate_SocialGroup,
          "Charisma", "Manipulation", "Appearance"),  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
      new TraitGroup(
          Messages.AttributeGroup_MentalLabel,
          Messages.AttributeGroupTemplate_MentalGroup,
          "Perception", "Intelligence", "Wits") };  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

  public TraitGroup[] getGroups() {
    return groups;
  }
}