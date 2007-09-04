package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public interface IAttributeCharacterContext {

  public IExperience getExperience();
  
  public IAttributes getAttributes();

  public ITraitGroup[] getTraitGroups();

  public ITraitTemplate getTraitTemplate();

}
