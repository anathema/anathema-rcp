package net.sf.anathema.character.core.traitview;


import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.traitview.internal.MouseInputAdapter;
import net.sf.anathema.character.core.traitview.internal.OuterPaintListener;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class CanvasIntValueDisplay implements IExtendableIntValueView, IRedrawable {

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

  public CanvasIntValueDisplay(Color background, Composite parent, Image passiveImage, Image valueImage, int maxValue) {
    this.paintListener = new IntValuePaintListener(new IValueContainer() {

      @Override
      public void redraw() {
        CanvasIntValueDisplay.this.redraw();
      }

      @Override
      public int getValue() {
        return value;
      }
    }, passiveImage, valueImage, maxValue);
    this.composite = createComposite(parent);
    this.composite.setBackground(background);
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
    canvas.setData(GridDataFactory.createRightAlign());
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