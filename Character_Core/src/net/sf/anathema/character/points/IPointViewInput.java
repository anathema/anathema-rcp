package net.sf.anathema.character.points;

import net.sf.anathema.character.core.model.ICharacterId;

public interface IPointViewInput {

  public IPointEntry[] createEntries();

  public ICharacterId getCharacterId();
}