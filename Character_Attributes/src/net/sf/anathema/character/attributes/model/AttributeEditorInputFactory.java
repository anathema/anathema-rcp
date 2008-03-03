package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.trait.groupeditor.AbstractTraitCollectionEditorInputFactory;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.model.ITraitGroupConfiguration;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AttributeEditorInputFactory extends AbstractTraitCollectionEditorInputFactory {

  @Override
  protected IEditorInputConfiguration createEditorInputConfiguration() {
    return new AttributesEditorInputConfiguration();
  }

  @Override
  protected ITraitGroupConfiguration createGroupConfiguration(ICharacterTemplate template) {
    return new AttributeGroupConfiguration();
  }

  @Override
  protected ITraitCollectionTemplate createTemplate(ICharacterTemplate template) {
    return new AttributeTemplateProvider().getAttributeTemplate(template.getId());
  }
}