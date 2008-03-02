package net.sf.anathema.character.caste.model;

import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.core.model.template.IModelTemplate;

public class CasteTemplate implements IModelTemplate {

  private final ICaste[] castes;
  private final String traitModelId;

  public CasteTemplate(String traitModelId, ICaste... castes) {
    this.traitModelId = traitModelId;
    this.castes = castes;
  }

  public ICaste[] getCastes() {
    return castes;
  }

  public String getTraitModelId() {
    return traitModelId;
  }
}