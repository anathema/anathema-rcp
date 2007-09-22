package net.sf.anathema.character.core.traitview;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class IntValuePaintContext implements IIntValuePaintContext {
  private final PaintEvent event;
  private final int value;
  private final IntDisplayArea area;

  public IntValuePaintContext(PaintEvent event, int value, IntDisplayArea area) {
    this.event = event;
    this.value = value;
    this.area = area;
  }

  @Override
  public void drawImage(int index, Image image) {
    event.gc.drawImage(image, area.getXPosition(index), 1);
  }

  @Override
  public int getValue() {
    return value;
  }
}