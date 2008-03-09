package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class AttributeTemplate extends AttributeGroupTemplate implements IModelTemplate, ITraitCollectionTemplate {

  private final int favorizationCount;
  private final AttributeTemplateFactory templateFactory = new AttributeTemplateFactory();

  public AttributeTemplate(int favorizationCount) {
    this.favorizationCount = favorizationCount;
  }

  public int getFavorizationCount() {
    return favorizationCount;
  }

  @Override
  public ITraitTemplate getTraitTemplate() {
    return templateFactory.getTraitTemplate();
  }
}