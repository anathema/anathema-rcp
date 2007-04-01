/**
 * 
 */
package net.sf.anathema.basics.swt.event;

import net.sf.anathema.lib.control.change.ChangeControl;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public final class MouseUpChangeAdapter extends MouseAdapter {

  private final ChangeControl changeControl;

  public MouseUpChangeAdapter(ChangeControl changeControl) {
    this.changeControl = changeControl;
  }

  @Override
  public void mouseUp(MouseEvent e) {
    changeControl.fireChangedEvent();
  }
}