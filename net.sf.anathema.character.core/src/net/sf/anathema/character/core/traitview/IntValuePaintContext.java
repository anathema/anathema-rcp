package net.sf.anathema.character.core.traitview;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public class IntValuePaintContext implements IIntValuePaintContext {
  private final GC gc;
  private final int value;
  private final IntDisplayArea area;

  public IntValuePaintContext(GC gc, int value, IntDisplayArea area) {
    this.gc = gc;
    this.value = value;
    this.area = area;
  }

  @Override
  public void drawImage(int index, Image image) {
    gc.drawImage(image, area.getXPosition(index), 1);
  }

  @Override
  public int getValue() {
    return value;
  }
}