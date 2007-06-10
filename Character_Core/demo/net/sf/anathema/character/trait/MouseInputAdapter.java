package net.sf.anathema.character.trait;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class MouseInputAdapter implements IMouseInputListener {

  @Override
  public void mouseDoubleClick(MouseEvent e) {
    // nothing to do
  }

  @Override
  public void mouseDown(MouseEvent e) {
    // nothing to do
  }

  @Override
  public void mouseUp(MouseEvent e) {
    // nothing to do
  }

  @Override
  public void mouseMove(MouseEvent e) {
    // nothing to do
  }

  public final void addTo(Composite composite) {
    composite.addMouseListener(this);
    composite.addMouseMoveListener(this);
  }

  public final void addTo(Control control) {
    control.addMouseListener(this);
    control.addMouseMoveListener(this);
  }
}