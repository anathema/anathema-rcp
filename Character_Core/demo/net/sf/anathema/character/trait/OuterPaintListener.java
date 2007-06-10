/**
 * 
 */
package net.sf.anathema.character.trait;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

public final class OuterPaintListener implements PaintListener {

  private final Rectangle rectangleToDraw = new Rectangle(0, 0, 0, 0);
  private final Control control;

  public OuterPaintListener(Control control) {
    this.control = control;
  }

  @Override
  public void paintControl(PaintEvent e) {
    if (rectangleToDraw.width == 0) {
      return;
    }
    e.gc.drawRectangle(rectangleToDraw);
    Color color = new Color(e.display, 120, 120, 120);
    e.gc.setAlpha(120);
    try {
      e.gc.setBackground(color);
      e.gc.fillRectangle(rectangleToDraw);
    }
    finally {
      color.dispose();
    }
  }

  public void resizeMarkerRectangle(int width) {
    rectangleToDraw.width = Math.min(width, control.getSize().x) - 1;
    rectangleToDraw.height = control.getSize().y - 1;
    control.redraw();
  }
}