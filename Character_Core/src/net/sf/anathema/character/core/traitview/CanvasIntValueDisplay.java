package net.sf.anathema.character.core.traitview;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.util.IClosure;
import net.sf.anathema.character.core.traitview.internal.MouseInputAdapter;
import net.sf.anathema.character.core.traitview.internal.OuterPaintListener;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class CanvasIntValueDisplay implements IExtendableIntValueView, IRedrawable {

  private final class IntValuePaintListener implements PaintListener {

    private final List<IIntValuePainter> allPainters = new ArrayList<IIntValuePainter>();
    private final IntDisplayArea area;

    public IntValuePaintListener(Image passiveImage, Image valueImage, int maxValue) {
      ImageData imageData = passiveImage.getImageData();
      this.area = new IntDisplayArea(imageData.height, imageData.width, maxValue);
      add(new BasicIntValuePainter(passiveImage, valueImage));
    }

    public IntDisplayArea getArea() {
      return area;
    }

    @Override
    public final void paintControl(PaintEvent e) {
      for (int index = 0; index < area.getMaxValue(); index++) {
        IntValuePaintContext context = new IntValuePaintContext(e, value, area);
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
      painter.init(CanvasIntValueDisplay.this, area.getImageHeight(), area.getImageHeight());
      allPainters.add(0, painter);
    }
  }

  private final class MouseDragListener extends MouseInputAdapter {
    private boolean isDrag;

    @Override
    public void mouseDown(MouseEvent e) {
      isDrag = true;
      fireValueChangedEventForCoordinate(e.x);
      updateMarkerRectangle(e.x);
    }

    @Override
    public void mouseMove(MouseEvent e) {
      if (!isDrag) {
        return;
      }
      fireValueChangedEventForCoordinate(e.x);
      updateMarkerRectangle(e.x);
    }

    @Override
    public void mouseUp(MouseEvent e) {
      if (isDrag) {
        updateMarkerRectangle(0);
      }
      isDrag = false;
    }

    private void updateMarkerRectangle(int width) {
      rectanglePainter.resizeMarkerRectangle(width);
    }
  }

  private final GenericControl<IIntValueChangedListener> control = new GenericControl<IIntValueChangedListener>();
  private OuterPaintListener rectanglePainter;
  private final MouseInputAdapter mouseListener = new MouseDragListener();
  private final Composite composite;
  private int value;
  private final IntValuePaintListener paintListener;

  public CanvasIntValueDisplay(Composite parent, Image passiveImage, Image valueImage, int maxValue) {
    this.paintListener = new IntValuePaintListener(passiveImage, valueImage, maxValue);
    this.composite = createComposite(parent);
  }

  private Composite createComposite(Composite parent) {
    Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED) {
      @Override
      public Rectangle computeTrim(int x, int y, int width, int height) {
        int preferredHeight = paintListener.getArea().getPreferredHeight();
        int preferredWidth = paintListener.getArea().getPreferredWidth();
        return new Rectangle(x, y, preferredWidth, preferredHeight);
      }
    };
    mouseListener.addTo(canvas);
    canvas.addPaintListener(paintListener);
    this.rectanglePainter = new OuterPaintListener(canvas);
    canvas.addPaintListener(rectanglePainter);
    return canvas;
  }

  public void addPainter(IIntValuePainter painter) {
    paintListener.add(painter);
  }

  private void fireValueChangedEventForCoordinate(int x) {
    fireValueChangedEvent(paintListener.getArea().getIndexForPosition(x));
  }

  @Override
  public void setValue(int newValue) {
    if (this.value == newValue) {
      return;
    }
    this.value = newValue;
    redraw();
  }
  
  public void setMaxValue(int maxValue) {
    if (maxValue == paintListener.area.getMaxValue()) {
      return;
    }
    paintListener.area.setMaxValue(maxValue);
    refreshLayout();
  }

  protected void refreshLayout() {
    composite.getParent().layout();
  }
  
  public void redraw() {
    composite.redraw();
  }

  private void fireValueChangedEvent(final int intValue) {
    control.forAllDo(new IClosure<IIntValueChangedListener>() {
      @Override
      public void execute(IIntValueChangedListener input) {
        input.valueChanged(intValue);
      }
    });
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    control.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    control.removeListener(listener);
  }
}