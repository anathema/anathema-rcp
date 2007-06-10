/**
 * 
 */
package net.sf.anathema.character.trait;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;

public final class LabelPaintListener implements PaintListener {

  private final Rectangle rectangleToDraw = new Rectangle(0, 0, 0, 0);
  private final Label label;

  public LabelPaintListener(Label label) {
    this.label = label;
  }

  @Override
  public void paintControl(PaintEvent e) {
    if (rectangleToDraw.width == 0) {
      return;
    }
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
    int labelPosition = label.getLocation().x;
    int rectangleWidth = Math.max(0, width - labelPosition);
    rectangleToDraw.width = Math.min(rectangleWidth, label.getSize().x);
    rectangleToDraw.height = label.getSize().y;
    label.redraw();
  }
}