package net.sf.anathema.character.abilities.util;

import net.sf.anathema.character.abilities.model.AbilitiesGroupTemplate;
import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.AbstractTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class AbilitiesDisplayGroupFactory extends AbstractTraitDisplayGroupFactory {
  public AbilitiesDisplayGroupFactory() {
    super(IAbilitiesPluginConstants.MODEL_ID, new AbilitiesTemplateProvider());
  }

  @Override
  protected ITraitGroupTemplate createGroupTemplate(ICharacter character) {
    return new AbilitiesGroupTemplate(character.getTemplate());
  }
}