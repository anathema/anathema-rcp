package net.sf.anathema.charms.character.fake;

import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class DummyPersistable implements IPersistableElement {
  public IMemento lastSavedMemento;

  @Override
  public String getFactoryId() {
    return null;
  }

  @Override
  public void saveState(IMemento memento) {
    this.lastSavedMemento = memento;
  }
}