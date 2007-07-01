package net.sf.anathema.character.trait.experience;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.experience.IExperience;

public class DummyExperienceModel extends AbstractModel implements IExperience {

  private boolean experienced;

  public DummyExperienceModel() {
    this(false);
  }

  public DummyExperienceModel(boolean experienced) {
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