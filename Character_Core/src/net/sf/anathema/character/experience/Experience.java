package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public final class Experience extends AbstractModel implements IExperience {
  private boolean experienced;
  private ChangeControl control = new ChangeControl();

  @Override
  public boolean isExperienced() {
    return experienced;
  }

  @Override
  public void setExperienced(boolean experienced) {
    if (this.experienced = experienced) {
      return;
    }
    this.experienced = experienced;
    this.control.fireChangedEvent();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    control.removeChangeListener(listener);
  }
}