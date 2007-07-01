package net.sf.anathema.character.attributes;

import net.sf.anathema.character.experience.ICharacterBasics;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitRules;

public interface IAttributeCharacterContext {

  public ICharacterBasics getBasics();

  public TraitGroup[] getTraitGroups();

  public ITraitRules getRules();

}
