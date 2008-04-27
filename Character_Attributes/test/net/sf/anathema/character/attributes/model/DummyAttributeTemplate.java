package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class DummyAttributeTemplate extends AttributeGroupTemplate implements IModelTemplate, ITraitCollectionTemplate {

  private final int favorizationCount;

  public DummyAttributeTemplate(int favorizationCount) {
    this.favorizationCount = favorizationCount;
  }

  public int getFavorizationCount() {
    return favorizationCount;
  }
}