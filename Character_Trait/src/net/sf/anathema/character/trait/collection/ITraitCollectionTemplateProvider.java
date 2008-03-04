package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public interface ITraitCollectionTemplateProvider {

  public ITraitCollectionTemplate getTraitTemplate(String characterTemplateId);
}