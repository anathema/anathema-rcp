package net.sf.anathema.character.spiritualtraits.model;

import net.sf.anathema.character.trait.collection.AbstractTraitCollectionFactory;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;

public class SpiritualTraitModelFactory extends AbstractTraitCollectionFactory {

  @Override
  protected ITraitCollectionTemplateProvider getTemplateProvider() {
    return new SpiritualTraitTemplateProvider();
  }
}