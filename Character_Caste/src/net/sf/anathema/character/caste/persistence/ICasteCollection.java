package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.character.caste.ICaste;

public interface ICasteCollection {

  public ICaste[] getCastes(String characterTypeId);
}