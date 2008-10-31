/**
 * 
 */
package net.sf.anathema.character.trait.persistence;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public final class StaticTraitGroupTemplate implements ITraitGroupTemplate {
  private final TraitGroup[] groups;

  public StaticTraitGroupTemplate(TraitGroup[] groups) {
    this.groups = groups;
  }

  @Override
  public TraitGroup[] getGroups() {
    return groups;
  }
}