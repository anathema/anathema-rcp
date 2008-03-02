package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public interface ITraitGroupConfiguration {

  public TraitGroup[] getGroups();

  public ITraitTemplate getTraitTemplate();

}