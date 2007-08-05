package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public class NullPointViewInput implements IPointViewInput {

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