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

  public class IntValuePaintContext implements IIntValuePaintContext {
    private final PaintEvent event;

    public IntValuePaintContext(PaintEvent event) {
      this.event = event;
    }

    @Override
    public void drawImage(int index, Image image) {
      event.gc.drawImage(image, getXPosition(index), 1);
    }

    @Override
    public int getValue() {
      return value;
    }
  }

  private class IntValuePaintListener implements PaintListener {

    private List<IIntValuePainter> allPainters = new ArrayList<IIntValuePainter>();
    private int imageHeight;
    private int imageWidth;

    public IntValuePaintListener(Image passiveImage, Image valueImage) {
      ImageData imageData = passiveImage.getImageData();
      this.imageHeight = imageData.height;
      this.imageWidth = imageData.width;
      add(new BasicIntValuePainter(passiveImage, valueImage));
    }
    
    public int getImageHeight() {
      return imageHeight;
    }
    
    public int getImageWidth() {
      return imageWidth;
    }

    @Override
    public final void paintControl(PaintEvent e) {
      for (int index = 0; index < maxValue; index++) {
        IntValuePaintContext context = new IntValuePaintContext(e);
        getResponsiblePainter(context, index).drawImage(context, index);
      }
    }

    private IIntValuePainter getResponsiblePainter(IntValuePaintContext context, int index) {
      for (IIntValuePainter painter : allPainters) {
        if (painter.isResponsable(context, index)) {
          return painter;
        }
      }
      throw new UnreachableCodeReachedException();
    }

    public void add(IIntValuePainter painter) {
      painter.init(CanvasIntValueDisplay.this, imageWidth, imageHeight);
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

  private static final int GROUP_SIZE = 5;
  private static final int HORIZONTAL_INDENT = 2;
  private final int maxValue;
  private final GenericControl<IIntValueChangedListener> control = new GenericControl<IIntValueChangedListener>();
  private OuterPaintListener rectanglePainter;
  private final MouseInputAdapter mouseListener = new MouseDragListener();
  private final Composite composite;
  private final int slotWidth;
  private final int whitespaceSlotWidth;
  private int value;
  private final IntValuePaintListener paintListener;

  public CanvasIntValueDisplay(Composite parent, Image passiveImage, Image valueImage, int maxValue) {
    this.paintListener = new IntValuePaintListener(passiveImage, valueImage);
    this.slotWidth = paintListener.getImageWidth() + 2;
    this.whitespaceSlotWidth = slotWidth / 2;
    this.maxValue = maxValue;
    this.composite = createComposite(parent);
  }

  private Composite createComposite(Composite parent) {
    Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED) {
      @Override
      public Rectangle computeTrim(int x, int y, int width, int height) {
        int preferredHeight = paintListener.getImageHeight() + 2;
        int preferredWidth = getXPosition(maxValue);
        if (maxValue % GROUP_SIZE == 0) {
          preferredWidth -= whitespaceSlotWidth;
        }
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
    if (x < slotWidth / 3) {
      fireValueChangedEvent(0);
      return;
    }
    if (x > getXPosition(maxValue - 1)) {
      fireValueChangedEvent(maxValue);
      return;
    }
    for (int imageIndex = 0; imageIndex < maxValue; imageIndex++) {
      if (x < getXPosition(imageIndex)) {
        fireValueChangedEvent(imageIndex);
        return;
      }
    }
  }
  
  @Override
  public void setValue(int newValue) {
    if (this.value == newValue) {
      return;
    }
    this.value = newValue;
    redraw();
  }
  
  

  public void redraw() {
    composite.redraw();
  }

  private int getXPosition(int imageIndex) {
    return HORIZONTAL_INDENT + imageIndex * slotWidth + getWhitespaceWidth(imageIndex);
  }

  private int getWhitespaceWidth(int index) {
    int whitespaceCount = index / GROUP_SIZE;
    return whitespaceCount * whitespaceSlotWidth;
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