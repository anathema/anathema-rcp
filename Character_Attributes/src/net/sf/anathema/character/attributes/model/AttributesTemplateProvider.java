package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.collection.TraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.IMinimalValueFactory;
import net.sf.anathema.character.trait.model.TraitTemplateFactory;

public class AttributesTemplateProvider extends TraitCollectionTemplateProvider {

  public AttributesTemplateProvider() {
    super(IAttributesPluginConstants.MODEL_ID);
  }

  @Override
  protected ITraitGroupTemplate createGroupConfiguration(String characterTemplateId) {
    return new AttributeGroupTemplate();
  }

  @Override
  protected IMinimalValueFactory createTemplateFactory(String characterTemplateId) {
    return new TraitTemplateFactory(1, characterTemplateId);
  }
}