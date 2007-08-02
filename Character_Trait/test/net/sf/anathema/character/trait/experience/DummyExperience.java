package net.sf.anathema.character.trait.experience;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.experience.model.IExperience;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class DummyExperience extends AbstractModel implements IExperience {

  private boolean experienced;
  private final ChangeControl control = new ChangeControl();

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
    control.fireChangedEvent();
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