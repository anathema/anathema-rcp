package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public interface IPointViewInput {

  public IValueEntry[] createEntries();

  public ICharacterId getCharacterId();
}