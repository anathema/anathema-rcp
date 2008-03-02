package net.sf.anathema.character.abilities.model;


import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.trait.groupeditor.AbstractTraitCollectionEditorInputFactory;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;


public class AbilitiesEditorInputFactory extends AbstractTraitCollectionEditorInputFactory {

  @Override
  protected AbilitiesGroupConfiguration createGroupConfiguration() {
    return new AbilitiesGroupConfiguration();
  }

  @Override
  protected AbilitiesEditorInputConfiguration createEditorInputConfiguration() {
    return new AbilitiesEditorInputConfiguration();
  }

  @Override
  protected ITraitCollectionTemplate createTemplate(ICharacterTemplate template) {
    return new AbilitiesTemplateProvider().getAttributeTemplate(template.getId());
  }
}