package net.sf.anathema.character.core.traitview;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.ui.IIntValueView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class CanvasIntValueDisplay implements IIntValueView {

  private static final int GROUP_SIZE = 5;
  private static final int HORIZONTAL_INDENT = 2;
  private final int maxValue;
  private final Image passiveImage;
  private final Image activeImage;
  private final GenericControl<IIntValueChangedListener> control = new GenericControl<IIntValueChangedListener>();
  private OuterPaintListener rectanglePainter;
  private final MouseInputAdapter mouseListener = new MouseInputAdapter() {

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
  };
  private final Composite composite;
  private final int slotWidth;
  private int value;

  public CanvasIntValueDisplay(Composite parent, Image passiveImage, Image activeImage, int maxValue) {
    ImageData passiveData = passiveImage.getImageData();
    ImageData activeData = activeImage.getImageData();
    Ensure.ensureArgumentEquals(passiveData.width, activeData.width);
    Ensure.ensureArgumentEquals(passiveData.height, activeData.height);
    this.slotWidth = passiveData.width + 2;
    this.passiveImage = passiveImage;
    this.activeImage = activeImage;
    this.maxValue = maxValue;
    this.composite = createComposite(parent);
  }

  private Composite createComposite(Composite parent) {
    Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED) {
      @Override
      public Rectangle computeTrim(int x, int y, int width, int height) {
        int preferredHeight = passiveImage.getImageData().height + 2;
        int preferredWidth = getXPosition(maxValue);
        return new Rectangle(x, y, preferredWidth, preferredHeight);
      }
    };
    mouseListener.addTo(canvas);
    canvas.addPaintListener(new PaintListener() {
      @Override
      public void paintControl(PaintEvent e) {
        for (int index = 0; index < value; index++) {
          addImage(e, index, activeImage);
        }
        for (int index = value; index < maxValue; index++) {
          addImage(e, index, passiveImage);
        }
      }

      private void addImage(PaintEvent e, int index, Image image) {
        e.gc.drawImage(image, getXPosition(index), 1);
      }
    });
    this.rectanglePainter = new OuterPaintListener(canvas);
    canvas.addPaintListener(rectanglePainter);
    return canvas;
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
    this.value = newValue;
    composite.redraw();
  }

  private int getXPosition(int imageIndex) {
    return HORIZONTAL_INDENT + imageIndex * slotWidth + getWhitespaceWidth(imageIndex);
  }

  private int getWhitespaceWidth(int index) {
    int whitespaceCount = index / GROUP_SIZE;
    return whitespaceCount * slotWidth / 2;
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