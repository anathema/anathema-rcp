package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributesFactory extends AbstractModelFactory<AttributeTemplate, ITraitCollectionModel> {

  private final IModelPersister<AttributeTemplate, ITraitCollectionModel> persister = new AttributesPersister();

  @Override
  protected AttributeTemplate createModelTemplate(ICharacterTemplate template) {
    return new AttributeTemplateProvider().getAttributeTemplate(template.getId());
  }

  @Override
  protected IModelPersister<AttributeTemplate, ITraitCollectionModel> getPersister() {
    return persister;
  }
}