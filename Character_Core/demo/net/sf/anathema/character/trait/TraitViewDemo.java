package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class TraitViewDemo implements IDemo {

  public static void main(String[] args) {
    new DemoRunner().run(new TraitViewDemo());
  }

  @Override
  public void createComposite(Composite parent) throws Exception {
    Image passiveImage = createImage("BorderUnselectedButton16.png");
    Image activeImage = createImage("BorderSolarButton16.png");
    final IntValueDisplay intValueDisplay = new IntValueDisplay(passiveImage, activeImage, 5);
    intValueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        intValueDisplay.setValue(newValue);
      }
    });
    Composite composite = intValueDisplay.createComposite(parent);
    composite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
    composite.pack();
  }

  private Image createImage(String imageName) {
    return ImageDescriptor.createFromFile(TraitViewDemo.class, imageName).createImage();
  }
}