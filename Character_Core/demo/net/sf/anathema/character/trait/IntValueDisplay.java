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
  private final MouseInputAdapter mouseListener = new MouseInputAdapter() {

    private boolean isDrag;

    @Override
    public void mouseMove(MouseEvent e) {
      if (!isDrag) {
        return;
      }
      fireValueChangedEvent(e.x, e.getSource());
      updateMarkerRectangle(e.x, e.getSource());
    }

    @Override
    public void mouseDown(MouseEvent e) {
      isDrag = true;
      fireValueChangedEvent(e.x, e.getSource());
      updateMarkerRectangle(e.x, e.getSource());
    }

    @Override
    public void mouseUp(MouseEvent e) {
      if (isDrag) {
        updateMarkerRectangle(e.x, e.getSource());
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

  protected void updateMarkerRectangle(int x, Object source) {
    // TODO Auto-generated method stub

  }

  protected void fireValueChangedEvent(int x, Object source) {
    int xPosition = x;
    if (source instanceof Label) {
      xPosition += ((Label) source).getLocation().x; 
    }
    if (xPosition < activeImage.getImageData().width / 3) {
      fireValueChangedEvent(0);
      return;
    }
    if (xPosition > labels.get(maxValue - 1).getLocation().x) {
      fireValueChangedEvent(maxValue);
      return;
    }
    for (int imageIndex = 0; imageIndex < maxValue; imageIndex++) {
      if (xPosition < labels.get(imageIndex).getLocation().x) {
        fireValueChangedEvent(imageIndex);
        return;
      }
    }
  }

  private void fireValueChangedEvent(final int intValue) {
    control.forAllDo(new IClosure<IIntValueChangedListener>() {
      @Override
      public void execute(IIntValueChangedListener input) {
        input.valueChanged(intValue);
      }
    });
  }

  public Composite createComposite(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    mouseListener.addTo(container);
    container.setLayout(new GridLayout(5, true));
    for (int index = 0; index < maxValue; index++) {
      Label newLabel = new Label(container, SWT.NULL);
      newLabel.setImage(passiveImage);
      mouseListener.addTo(newLabel);
      labels.add(newLabel);
    }
    return container;
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    control.addListener(listener);
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
}