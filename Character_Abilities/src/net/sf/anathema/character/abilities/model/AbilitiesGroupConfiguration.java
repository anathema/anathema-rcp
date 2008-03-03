package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupConfiguration;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class AbilitiesGroupConfiguration implements ITraitGroupConfiguration {

  private final ICharacterTemplate characterTemplate;

  public AbilitiesGroupConfiguration(ICharacterTemplate characterTemplate) {
    this.characterTemplate = characterTemplate;
  }

  public TraitGroup[] getGroups() {
    return new AbilityGroupsProvider().getTraitGroups(characterTemplate.getCharacterTypeId());
  }

  public ITraitTemplate getTraitTemplate() {
    EssenceSensitiveTraitTemplate traitTemplate = new EssenceSensitiveTraitTemplate();
    traitTemplate.setMiniumalValue(0);
    return traitTemplate;
  }
}