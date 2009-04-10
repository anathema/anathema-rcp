package net.sf.anathema.character.core.traitview;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.exception.UnreachableCodeReachedException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public final class IntValuePaintListener implements PaintListener {

  private final List<IIntValuePainter> allPainters = new ArrayList<IIntValuePainter>();
  final IntDisplayArea area;
  private final IValueContainer redrawable;

  public static IntDisplayArea createDisplayArea(Image passiveImage, int maxValue) {
    ImageData imageData = passiveImage.getImageData();
    return new IntDisplayArea(imageData.height, imageData.width, maxValue);
  }

  public IntValuePaintListener(IValueContainer redrawable, Image passiveImage, Image valueImage, int maxValue) {
    this.redrawable = redrawable;
    this.area = createDisplayArea(passiveImage, maxValue);
    add(new BasicIntValuePainter(passiveImage, valueImage));
  }

  public IntDisplayArea getArea() {
    return area;
  }

  @Override
  public final void paintControl(PaintEvent e) {
    GC gc = e.gc;
    paintControl(gc);
  }

  public void paintControl(GC gc) {
    for (int index = 0; index < area.getMaxValue(); index++) {
      IntValuePaintContext context = new IntValuePaintContext(gc, redrawable.getValue(), area);
      getResponsiblePainter(context, index).drawImage(context, index);
    }
  }

  private IIntValuePainter getResponsiblePainter(IntValuePaintContext context, int index) {
    for (IIntValuePainter painter : allPainters) {
      if (painter.isResponsible(context, index)) {
        return painter;
      }
    }
    throw new UnreachableCodeReachedException();
  }

  public void add(IIntValuePainter painter) {
    painter.init(redrawable, area.getImageHeight(), area.getImageHeight());
    allPainters.add(0, painter);
  }
}