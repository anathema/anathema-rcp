package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.groupeditor.AbstractTraitCollectionEditorInputFactory;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;

public class AttributeEditorInputFactory extends AbstractTraitCollectionEditorInputFactory {

  @Override
  protected IEditorInputConfiguration createEditorInputConfiguration() {
    return new AttributesEditorInputConfiguration();
  }

  @Override
  public ITraitCollectionTemplateProvider getTraitTemplateProvider() {
    return new AttributesTemplateProvider();
  }
}