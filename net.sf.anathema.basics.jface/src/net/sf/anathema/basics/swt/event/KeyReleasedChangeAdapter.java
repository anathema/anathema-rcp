package net.sf.anathema.basics.swt.event;

import net.sf.anathema.lib.control.change.ChangeControl;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public final class KeyReleasedChangeAdapter extends KeyAdapter {
  private final ChangeControl changeControl;

  public KeyReleasedChangeAdapter(ChangeControl changeControl) {
    this.changeControl = changeControl;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    changeControl.fireChangedEvent();
  }
}