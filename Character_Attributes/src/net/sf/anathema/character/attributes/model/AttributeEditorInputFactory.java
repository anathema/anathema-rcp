package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.trait.groupeditor.AbstractTraitCollectionEditorInputFactory;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.ITraitTemplateFactory;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AttributeEditorInputFactory extends AbstractTraitCollectionEditorInputFactory {

  @Override
  protected IEditorInputConfiguration createEditorInputConfiguration() {
    return new AttributesEditorInputConfiguration();
  }

  @Override
  protected ITraitGroupTemplate createGroupTemplate(ICharacterTemplate template) {
    return new AttributeGroupTemplate();
  }

  @Override
  protected ITraitCollectionTemplate createTemplate(ICharacterTemplate characterTemplate) {
    return new AttributesTemplateProvider().getTraitTemplate(characterTemplate.getId());
  }

  @Override
  protected ITraitTemplateFactory createTemplateFactory(ICharacterTemplate template) {
    return new AttributeTemplateFactory();
  }
}