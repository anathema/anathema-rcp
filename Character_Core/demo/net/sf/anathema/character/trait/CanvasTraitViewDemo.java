package net.sf.anathema.character.trait;

import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.ui.IIntValueView;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class CanvasTraitViewDemo implements IDemo {
  public static void main(String[] args) throws Exception {
    new DemoRunner().run(new CanvasTraitViewDemo());
  }

  private Image passiveImage;
  private Image activeImage;

  @Override
  public void createComposite(Composite parent) throws Exception {
    this.passiveImage = createImage("BorderUnselectedButton16.png"); //$NON-NLS-1$
    this.activeImage = createImage("BorderSolarButton16.png"); //$NON-NLS-1$
    final IIntValueView intValueDisplay = new CanvasIntValueDisplay(parent, passiveImage, activeImage, 10);
    intValueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        intValueDisplay.setValue(newValue);
      }
    });
  }

  private Image createImage(String imageName) {
    return ImageDescriptor.createFromFile(CanvasIntValueDisplay.class, imageName).createImage();
  }

  @Override
  public void dispose() {
    passiveImage.dispose();
    activeImage.dispose();
  }
}