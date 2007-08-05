package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public interface ICharacterValueEntryFactory {

  public ICharacterId getCharacterId();

  public IValueEntry[] createEntries();
}