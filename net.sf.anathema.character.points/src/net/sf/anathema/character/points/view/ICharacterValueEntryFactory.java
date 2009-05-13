package net.sf.anathema.character.points.view;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public interface ICharacterValueEntryFactory {

  public ICharacterId getCharacterId();

  public List<IValueEntry> createEntries();
}