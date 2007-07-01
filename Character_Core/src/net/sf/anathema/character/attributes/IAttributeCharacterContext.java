package net.sf.anathema.character.attributes;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitRules;

public interface IAttributeCharacterContext {

  public IExperience getExperience();
  
  public IAttributes getAttribute();

  public TraitGroup[] getTraitGroups();

  public ITraitRules getRules();

}
