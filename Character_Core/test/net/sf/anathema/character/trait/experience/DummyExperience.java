package net.sf.anathema.character.trait.experience;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.experience.IExperience;

public class DummyExperience extends AbstractModel implements IExperience {

  private boolean experienced;

  public DummyExperience() {
    this(false);
  }

  public DummyExperience(boolean experienced) {
    this.experienced = experienced;
  }

  @Override
  public boolean isExperienced() {
    return experienced;
  }

  @Override
  public void setExperienced(boolean experienced) {
    this.experienced = experienced;
  }
}