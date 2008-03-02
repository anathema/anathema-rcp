package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AttributeTemplate extends AttributeGroupConfiguration implements IModelTemplate, ITraitCollectionTemplate {

  private final int favorizationCount;

  public AttributeTemplate(int favorizationCount) {
    this.favorizationCount = favorizationCount;
  }

  public int getFavorizationCount() {
    return favorizationCount;
  }
}