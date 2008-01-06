package net.sf.anathema.character.caste.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;

public class CasteTemplate implements IModelTemplate {
  
  private final String[] castes;

  public CasteTemplate(String... castes) {
    this.castes = castes;
  }

  public String[] getCastes() {
    return castes;
  }
}