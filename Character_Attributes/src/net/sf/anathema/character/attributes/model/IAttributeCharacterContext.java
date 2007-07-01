package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitRules;

public interface IAttributeCharacterContext {

  public IExperience getExperience();
  
  public IAttributes getAttributes();

  public TraitGroup[] getTraitGroups();

  public ITraitRules getRules();

}
