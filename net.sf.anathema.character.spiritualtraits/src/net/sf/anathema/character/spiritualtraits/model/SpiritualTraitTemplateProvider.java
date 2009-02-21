package net.sf.anathema.character.spiritualtraits.model;

import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;

public class SpiritualTraitTemplateProvider implements ITraitCollectionTemplateProvider {

  @Override
  public ITraitCollectionTemplate getTraitTemplate(String characterTemplateId) {
    return new SpiritualTraitCollectionTemplate();
  }
}