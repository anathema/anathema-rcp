package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.collection.TraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.model.ITraitGroupConfiguration;

public class AttributesTemplateProvider extends TraitCollectionTemplateProvider {

  public AttributesTemplateProvider() {
    super(IAttributesPluginConstants.MODEL_ID);
  }

  @Override
  protected ITraitGroupConfiguration createGroupConfiguration(String characterTemplateId) {
    return new AttributeGroupConfiguration();
  }
}