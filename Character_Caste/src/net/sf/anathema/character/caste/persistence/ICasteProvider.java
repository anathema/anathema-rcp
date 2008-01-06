package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.character.caste.model.ICaste;

public interface ICasteProvider {

  public ICaste[] getCastes(String characterTypeId);
}