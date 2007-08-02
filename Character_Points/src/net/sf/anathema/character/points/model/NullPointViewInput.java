package net.sf.anathema.character.points.model;

import net.sf.anathema.character.core.model.ICharacterId;

public class NullPointViewInput implements IPointViewInput {

  private IPointEntry[] pointEntries = new IPointEntry[0];

  @Override
  public IPointEntry[] createEntries() {
    return pointEntries;
  }

  @Override
  public ICharacterId getCharacterId() {
    return null;
  }
}