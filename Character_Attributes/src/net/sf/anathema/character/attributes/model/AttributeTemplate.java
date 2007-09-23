package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;

public class AttributeTemplate extends AttributeGroupConfiguration implements IModelTemplate {
  
  private final int favorizationCount;
  
  public AttributeTemplate(int favorizationCount) {
    this.favorizationCount = favorizationCount;
  }
  
  public int getFavorizationCount() {
    return favorizationCount;
  }
}