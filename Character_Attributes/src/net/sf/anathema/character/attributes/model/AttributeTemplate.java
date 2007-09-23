package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;
import net.sf.anathema.character.trait.rules.TraitTemplate;

public class AttributeTemplate implements IModelTemplate {

  private final TraitGroup[] groups = new TraitGroup[] {
      new TraitGroup(Messages.AttributeGroup_PhysicalLabel, "Strength", "Dexterity", "Stamina"),  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ 
      new TraitGroup(Messages.AttributeGroup_SocialLabel, "Charisma", "Manipulation", "Appearance"),  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
      new TraitGroup(Messages.AttributeGroup_MentalLabel, "Perception", "Intelligence", "Wits") };  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ 

  public TraitGroup[] getGroups() {
    return groups;
  }

  public ITraitTemplate getTraitTemplate() {
    TraitTemplate traitTemplate = new TraitTemplate();
    traitTemplate.setMiniumalValue(1);
    return traitTemplate;
  }
}