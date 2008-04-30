package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;

public interface ITraitCollectionTemplateProvider {

  public ITraitCollectionTemplate getTraitTemplate(String characterTemplateId);
}