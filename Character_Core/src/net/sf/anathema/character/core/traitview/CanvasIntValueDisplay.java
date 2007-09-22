package net.sf.anathema.character.core.traitview;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.IClosure;
import net.sf.anathema.character.core.traitview.internal.MouseInputAdapter;
import net.sf.anathema.character.core.traitview.internal.OuterPaintListener;
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

  public interface IIntValuePainter {
    
    public void drawImage(PaintEvent e, int index);
  }
  
  public class IntValuePaintContext implements IIntValuePaintContext {
    private final PaintEvent event;

    public IntValuePaintContext(PaintEvent event) {
      this.event = event;
    }

    @Override
    public void drawImage(int index, Image image) {
      event.gc.drawImage(image, getXPosition(index), 1);
    }
  }
  
  private class CorePaintListener implements PaintListener {
    @Override
    public final void paintControl(PaintEvent e) {
      for (int index = 0; index < maxValue; index++) {
        drawImage(new IntValuePaintContext(e), index);
      }
    }

    public void drawImage(IIntValuePaintContext context, int index) {
      if (index < value) {
        context.drawImage(index, valueImage);
      }
      else {
        context.drawImage(index, passiveImage);
      }
    }
  }

  private final class SurplusPaintListener extends CorePaintListener {

    @Override
    public void drawImage(IIntValuePaintContext context, int index) {
      if (showSurplus && surplusValue <= index && index < value) {
        context.drawImage(index, surplusImage);
      }
      else {
        super.drawImage(context, index);
      }
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
  private final Image passiveImage;
  private final Image valueImage;
  private final Image surplusImage;
  private final GenericControl<IIntValueChangedListener> control = new GenericControl<IIntValueChangedListener>();
  private OuterPaintListener rectanglePainter;
  private final MouseInputAdapter mouseListener = new MouseDragListener();
  private final Composite composite;
  private final int slotWidth;
  private final int whitespaceSlotWidth;
  private int value;
  private int surplusValue;
  private boolean showSurplus;

  public CanvasIntValueDisplay(Composite parent, Image passiveImage, Image valueImage, Image surplusImage, int maxValue) {
    ImageData passiveData = passiveImage.getImageData();
    ImageData valueData = valueImage.getImageData();
    ImageData surplusData = surplusImage.getImageData();
    Ensure.ensureArgumentEquals(passiveData.width, valueData.width);
    Ensure.ensureArgumentEquals(passiveData.width, surplusData.width);
    Ensure.ensureArgumentEquals(passiveData.height, valueData.height);
    Ensure.ensureArgumentEquals(passiveData.height, surplusData.height);
    this.slotWidth = passiveData.width + 2;
    this.whitespaceSlotWidth = slotWidth / 2;
    this.passiveImage = passiveImage;
    this.valueImage = valueImage;
    this.surplusImage = surplusImage;
    this.maxValue = maxValue;
    this.composite = createComposite(parent);
  }

  private Composite createComposite(Composite parent) {
    Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED) {
      @Override
      public Rectangle computeTrim(int x, int y, int width, int height) {
        int preferredHeight = passiveImage.getImageData().height + 2;
        int preferredWidth = getXPosition(maxValue);
        if (maxValue % GROUP_SIZE == 0) {
          preferredWidth -= whitespaceSlotWidth;
        }
        return new Rectangle(x, y, preferredWidth, preferredHeight);
      }
    };
    mouseListener.addTo(canvas);
    canvas.addPaintListener(new SurplusPaintListener());
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
    if (this.value == newValue) {
      return;
    }
    this.value = newValue;
    composite.redraw();
  }

  public void setSurplusThreshold(int surplus) {
    if (this.surplusValue == surplus) {
      return;
    }
    this.surplusValue = surplus;
    composite.redraw();
  }

  public void setSurplusVisible(boolean enabled) {
    this.showSurplus = enabled;
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