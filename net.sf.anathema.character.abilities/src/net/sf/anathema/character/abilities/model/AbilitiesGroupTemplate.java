package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class AbilitiesGroupTemplate implements ITraitGroupTemplate {

  private final ICharacterTemplate characterTemplate;

  public AbilitiesGroupTemplate(ICharacterTemplate characterTemplate) {
    this.characterTemplate = characterTemplate;
  }

  public TraitGroup[] getGroups() {
    return new AbilityGroupsExtensionPoint().getTraitGroups(characterTemplate.getCharacterTypeId());
  }
}