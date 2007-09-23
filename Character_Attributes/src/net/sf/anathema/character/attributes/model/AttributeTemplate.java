package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;

public class AttributeTemplate extends AttributeGroupConfiguration implements IModelTemplate {
  
  private final int favorizationCount;
  
  // TODO NOW Muss wech und ran an den echten
  public AttributeTemplate() {
    this(2);
  }

  public AttributeTemplate(int favorizationCount) {
    this.favorizationCount = favorizationCount;
  }
  
  public int getFavorizationCount() {
    return favorizationCount;
  }
}