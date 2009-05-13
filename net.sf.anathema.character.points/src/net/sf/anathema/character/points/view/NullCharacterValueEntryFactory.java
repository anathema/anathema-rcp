package net.sf.anathema.character.points.view;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public class NullCharacterValueEntryFactory implements ICharacterValueEntryFactory {

  @Override
  public List<IValueEntry> createEntries() {
    return new ArrayList<IValueEntry>();
  }

  @Override
  public ICharacterId getCharacterId() {
    return null;
  }
}