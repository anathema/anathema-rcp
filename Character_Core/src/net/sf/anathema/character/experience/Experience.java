package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModel;

public final class Experience extends AbstractModel implements IExperience {
  private boolean experienced;

  @Override
  public boolean isExperienced() {
    return experienced;
  }

  @Override
  public void setExperienced(boolean experienced) {
    this.experienced = experienced;
  }
}