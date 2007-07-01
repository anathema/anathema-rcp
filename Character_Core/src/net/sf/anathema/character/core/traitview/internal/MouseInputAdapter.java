package net.sf.anathema.character.core.traitview.internal;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

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
}