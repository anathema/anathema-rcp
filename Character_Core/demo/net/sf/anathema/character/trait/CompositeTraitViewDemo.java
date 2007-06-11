package net.sf.anathema.character.trait;

import org.eclipse.swt.graphics.Image;

public class CompositeTraitViewDemo extends AbstractTraitViewDemo {
  public static void main(String[] args) {
    new DemoRunner().run(new CompositeTraitViewDemo());
  }

  @Override
  protected ISWTIntValueDisplay createDisplay(Image passiveImage, Image activeImage) {
    return new CompositeIntValueDisplay(passiveImage, activeImage, 5);
  }
}