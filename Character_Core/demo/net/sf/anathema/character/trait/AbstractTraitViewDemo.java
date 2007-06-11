package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractTraitViewDemo implements IDemo {

  @Override
  public void createComposite(Composite parent) throws Exception {
    Image passiveImage = createImage("BorderUnselectedButton16.png"); //$NON-NLS-1$
    Image activeImage = createImage("BorderSolarButton16.png"); //$NON-NLS-1$
    final ISWTIntValueDisplay intValueDisplay = createDisplay(passiveImage, activeImage);
    intValueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        intValueDisplay.setValue(newValue);
      }
    });
    Composite composite = intValueDisplay.createComposite(parent);
    composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    composite.pack();
  }

  protected abstract ISWTIntValueDisplay createDisplay(Image passiveImage, Image activeImage);

  private Image createImage(String imageName) {
    return ImageDescriptor.createFromFile(AbstractTraitViewDemo.class, imageName).createImage();
  }

}
