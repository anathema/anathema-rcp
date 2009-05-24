package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModel;

public final class Experience extends AbstractModel implements IExperience {

  public static IExperience Create(boolean experienced) {
    Experience experience = new Experience();
    experience.setExperienced(experienced);
    return experience;
  }

  private boolean experienced;

  @Override
  public boolean isExperienced() {
    return experienced;
  }

  @Override
  public void setExperienced(boolean experienced) {
    if (this.experienced == experienced) {
      return;
    }
    this.experienced = experienced;
    fireChangedEvent();
  }

  @Override
  protected void loadFromFromSaveState(Object memento) {
    this.experienced = (Boolean) memento;
  }

  @Override
  public Boolean getSaveState() {
    return experienced;
  }
}