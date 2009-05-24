package net.sf.anathema.character.core.fake;

import net.sf.anathema.character.core.model.AbstractModel;

public class DummyModel extends AbstractModel {

  public void fireModelChanged() {
    fireChangedEvent();
  }

  @Override
  protected void loadFromFromSaveState(Object memento) {
    // nothing to do
  }

  @Override
  public Object getSaveState() {
    return null;
  }
}