package net.sf.anathema.character.abilities.template;

import net.sf.anathema.character.abilities.model.AbilitiesGroupTemplate;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.collection.TraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.IMinimalValueFactory;
import net.sf.anathema.character.trait.model.MinimalValueFactory;

public class AbilitiesTemplateProvider extends TraitCollectionTemplateProvider {

  public AbilitiesTemplateProvider() {
    super(IAbilitiesPluginConstants.MODEL_ID);
  }

  @Override
  protected ITraitGroupTemplate createGroupConfiguration(String characterTemplateId) {
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterTemplateId);
    return new AbilitiesGroupTemplate(template);
  }

  @Override
  protected IMinimalValueFactory createTemplateFactory(String characterTemplateId) {
    return new MinimalValueFactory(0, characterTemplateId);
  }
}