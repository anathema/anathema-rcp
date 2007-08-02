package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.experience.model.IExperience;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public interface IAttributeCharacterContext {

  public IExperience getExperience();
  
  public IAttributes getAttributes();

  public TraitGroup[] getTraitGroups();

  public ITraitTemplate getRules();

}
