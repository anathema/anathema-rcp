package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

public class AttributesFactory extends AbstractModelFactory<AttributeTemplate> implements IModelFactory {

  private final IModelPersister<AttributeTemplate, ?> persister = new AttributesPersister();
  
  @Override
  protected AttributeTemplate createModelTemplate(ICharacterTemplate template) {
    return new AttributeTemplateProvider().getAttributeTemplate(template.getId());
  }

  @Override
  protected IModelPersister<AttributeTemplate, ?> getPersister() {
    return persister;
  }
}