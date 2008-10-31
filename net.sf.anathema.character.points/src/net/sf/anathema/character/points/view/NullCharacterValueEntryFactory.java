package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public class NullCharacterValueEntryFactory implements ICharacterValueEntryFactory {

  private IValueEntry[] pointEntries = new IValueEntry[0];

  @Override
  public IValueEntry[] createEntries() {
    return pointEntries;
  }

  @Override
  public ICharacterId getCharacterId() {
    return null;
  }
}