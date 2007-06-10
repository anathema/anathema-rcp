package net.sf.anathema.character.trait;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.ui.IIntValueView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class IntValueDisplay implements IIntValueView {

  private List<Label> labels;
  private final int maxValue;
  private final Image passiveImage;
  private final Image activeImage;
  private final GenericControl<IIntValueChangedListener> control = new GenericControl<IIntValueChangedListener>();
  private OuterPaintListener rectanglePainter;
  private final List<LabelPaintListener> labelPainters = new ArrayList<LabelPaintListener>();
  private final MouseInputAdapter mouseListener = new MouseInputAdapter() {

    private boolean isDrag;

    @Override
    public void mouseDown(MouseEvent e) {
      isDrag = true;
      int x = getXPosition(e.x, e.getSource());
      fireValueChangedEventForCoordinate(x);
      updateMarkerRectangle(x);
    }

    @Override
    public void mouseMove(MouseEvent e) {
      if (!isDrag) {
        return;
      }
      int x = getXPosition(e.x, e.getSource());
      fireValueChangedEventForCoordinate(x);
      updateMarkerRectangle(x);
    }

    @Override
    public void mouseUp(MouseEvent e) {
      if (isDrag) {
        updateMarkerRectangle(0);
      }
      isDrag = false;
    }
  };

  public IntValueDisplay(Image passiveImage, Image activeImage, int maxValue) {
    this.passiveImage = passiveImage;
    this.activeImage = activeImage;
    this.maxValue = maxValue;
    this.labels = new ArrayList<Label>(maxValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    control.addListener(listener);
  }

  public Composite createComposite(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    mouseListener.addTo(container);
    GridLayout gridLayout = new GridLayout(5, true);
    gridLayout.horizontalSpacing = 2;
    gridLayout.verticalSpacing = 2;
    gridLayout.marginHeight = 2;
    gridLayout.marginWidth = 2;
    container.setLayout(gridLayout);
    rectanglePainter = new OuterPaintListener(container);
    container.addPaintListener(rectanglePainter);
    for (int index = 0; index < maxValue; index++) {
      Label newLabel = new Label(container, SWT.NULL);
      LabelPaintListener labelRectanglePainter = new LabelPaintListener(newLabel);
      labelPainters.add(labelRectanglePainter);
      newLabel.addPaintListener(labelRectanglePainter);
      newLabel.setImage(passiveImage);
      mouseListener.addTo(newLabel);
      labels.add(newLabel);
    }
    return container;
  }

  private void fireValueChangedEvent(final int intValue) {
    control.forAllDo(new IClosure<IIntValueChangedListener>() {
      @Override
      public void execute(IIntValueChangedListener input) {
        input.valueChanged(intValue);
      }
    });
  }

  protected void fireValueChangedEventForCoordinate(int x) {
    if (x < activeImage.getImageData().width / 3) {
      fireValueChangedEvent(0);
      return;
    }
    if (x > labels.get(maxValue - 1).getLocation().x) {
      fireValueChangedEvent(maxValue);
      return;
    }
    for (int imageIndex = 0; imageIndex < maxValue; imageIndex++) {
      if (x < labels.get(imageIndex).getLocation().x) {
        fireValueChangedEvent(imageIndex);
        return;
      }
    }
  }

  private int getXPosition(int x, Object source) {
    int xPosition = x;
    if (source instanceof Label) {
      xPosition += ((Label) source).getLocation().x;
    }
    return xPosition;
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    control.removeListener(listener);
  }

  @Override
  public void setValue(int newValue) {
    for (int index = 0; index < newValue; index++) {
      labels.get(index).setImage(activeImage);
    }
    for (int index = newValue; index < maxValue; index++) {
      labels.get(index).setImage(passiveImage);
    }
  }

  private void updateMarkerRectangle(int width) {
    rectanglePainter.resizeMarkerRectangle(width);
    for (LabelPaintListener labelPainter : labelPainters) {
      labelPainter.resizeMarkerRectangle(width);
    }
  }
}