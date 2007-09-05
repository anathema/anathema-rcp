package net.sf.anathema.lib.control.change;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;

public class ChangeControl implements IChangeable {

  private final List<IChangeListener> listeners = new ArrayList<IChangeListener>();

  private synchronized List<IChangeListener> cloneListeners() {
    return new ArrayList<IChangeListener>(listeners);
  }

  public void fireChangedEvent() {
    for (IChangeListener listener : cloneListeners()) {
      listener.stateChanged();
    }
  }

  public void addChangeListener(IChangeListener listener) {
    listeners.add(listener);
  }

  public void removeChangeListener(IChangeListener listener) {
    listeners.remove(listener);
  }

  public void clear() {
    listeners.clear();
  }

  public int getListenerCount() {
    return listeners.size();
  }
}