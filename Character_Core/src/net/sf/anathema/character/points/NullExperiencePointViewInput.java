package net.sf.anathema.character.points;

import net.sf.anathema.character.core.model.ICharacterId;

public class NullExperiencePointViewInput implements IPointViewInput {

  private IPointEntry[] experiencePointEntries = new IPointEntry[0];

  @Override
  public IPointEntry[] createEntries() {
    return experiencePointEntries;
  }

  @Override
  public ICharacterId getCharacterId() {
    return null;
  }
}