package net.sf.anathema.character.trait;

import org.eclipse.swt.graphics.Image;

public class CanvasTraitViewDemo extends AbstractTraitViewDemo {
  public static void main(String[] args) {
    new DemoRunner().run(new CanvasTraitViewDemo());
  }

  @Override
  protected ISWTIntValueDisplay createDisplay(Image passiveImage, Image activeImage) {
    return new CanvasIntValueDisplay(passiveImage, activeImage, 5);
  }
}