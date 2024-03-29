package net.sf.anathema.character.trait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.character.core.traitview.SurplusPainter;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class CanvasTraitViewDemo implements IDemo {
  public static void main(String[] args) throws Exception {
    new DemoRunner().run(new CanvasTraitViewDemo());
  }

  private Image passiveImage;
  private Image activeImage;
  private Image surplusImage;

  @Override
  public void createComposite(Composite parent) throws Exception {
    this.passiveImage = createImage("BorderUnselectedButton16.png"); //$NON-NLS-1$
    URL url = new File("../CharacterType_Solar/icons/" + "Ball_Solar_16.png").toURI().toURL(); //$NON-NLS-1$ //$NON-NLS-2$
    this.activeImage = ImageDescriptor.createFromURL(url).createImage();
    this.surplusImage = createImage("BorderBonusButton16.png"); //$NON-NLS-1$
    final CanvasIntValueDisplay intValueDisplay = new CanvasIntValueDisplay(null, parent, passiveImage, activeImage, 10);
    intValueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        intValueDisplay.setValue(newValue);
      }
    });
    final SurplusPainter surplusPainter = new SurplusPainter(surplusImage);
    intValueDisplay.addPainter(surplusPainter);
    surplusPainter.setSurplusThreshold(3);
    final Button button = new Button(parent, SWT.TOGGLE);
    button.setText("Show Surplus"); //$NON-NLS-1$
    button.addListener(SWT.MouseUp, new Listener() {
      @Override
      public void handleEvent(Event event) {
        surplusPainter.setShowSurplus(button.getSelection());
      }
    });
  }

  private Image createImage(String imageName) throws MalformedURLException {
    URL url = new File("../Character_Trait/icons/" + imageName).toURI().toURL(); //$NON-NLS-1$
    return ImageDescriptor.createFromURL(url).createImage();
  }

  @Override
  public void dispose() {
    passiveImage.dispose();
    activeImage.dispose();
    surplusImage.dispose();
  }
}