package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;

public class AbilitiesTemplate extends AbilitiesGroupConfiguration implements IModelTemplate {
  
  private final int favorizationCount;
  
  public AbilitiesTemplate(int favorizationCount) {
    this.favorizationCount = favorizationCount;
  }
  
  public int getFavorizationCount() {
    return favorizationCount;
  }
}