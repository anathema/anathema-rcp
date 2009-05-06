package net.sf.anathema.character.attributes.model;

import static net.sf.anathema.character.attributes.model.IAttributeIds.*;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class AttributeGroupTemplate implements ITraitGroupTemplate {

  private static final TraitGroup[] groups = new TraitGroup[] {
      new TraitGroup(
          Messages.AttributeGroup_PhysicalLabel,
          Messages.AttributeGroupTemplate_PhysicalGroup,
          STRENGTH,
          DEXTERITY,
          STAMINA),
      new TraitGroup(
          Messages.AttributeGroup_SocialLabel,
          Messages.AttributeGroupTemplate_SocialGroup,
          CHARISMA,
          MANIPULATION,
          APPEARANCE),
      new TraitGroup(
          Messages.AttributeGroup_MentalLabel,
          Messages.AttributeGroupTemplate_MentalGroup,
          PERCEPTION,
          INTELLIGENCE,
          WITS) };

  public TraitGroup[] getGroups() {
    return groups;
  }
}