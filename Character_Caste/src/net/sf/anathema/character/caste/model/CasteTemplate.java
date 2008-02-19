package net.sf.anathema.character.caste.model;

import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.core.model.template.IModelTemplate;

public class CasteTemplate implements IModelTemplate {
  
  private final ICaste[] castes;

  public CasteTemplate(ICaste... castes) {
    this.castes = castes;
  }

  public ICaste[] getCastes() {
    return castes;
  }
}