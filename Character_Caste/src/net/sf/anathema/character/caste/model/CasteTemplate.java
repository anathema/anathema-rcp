package net.sf.anathema.character.caste.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;

public class CasteTemplate implements IModelTemplate {

  public String[] getCastes() {
    return new String[] { "Dawn", "Zenith", "Twilight", "Night", "Eclipse" };
  }
}