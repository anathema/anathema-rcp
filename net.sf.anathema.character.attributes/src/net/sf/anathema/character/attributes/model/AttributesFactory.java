package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.collection.AbstractTraitCollectionFactory;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;

public class AttributesFactory extends AbstractTraitCollectionFactory {

  @Override
  public ITraitCollectionTemplateProvider getTemplateProvider() {
    return new AttributesTemplateProvider();
  }
}