package net.sf.anathema.character.trait;

import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.ui.IIntValueView;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class CanvasTraitViewDemo implements IDemo {
  public static void main(String[] args) {
    new DemoRunner().run(new CanvasTraitViewDemo());
  }

  @Override
  public void createComposite(Composite parent) throws Exception {
    Image passiveImage = createImage("BorderUnselectedButton16.png"); //$NON-NLS-1$
    Image activeImage = createImage("BorderSolarButton16.png"); //$NON-NLS-1$
    final IIntValueView intValueDisplay = new CanvasIntValueDisplay(parent, passiveImage, activeImage, 10);
    intValueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        intValueDisplay.setValue(newValue);
      }
    });
  }

  // TODO Dispose of image
  private Image createImage(String imageName) {
    return ImageDescriptor.createFromFile(CanvasIntValueDisplay.class, imageName).createImage();
  }
}