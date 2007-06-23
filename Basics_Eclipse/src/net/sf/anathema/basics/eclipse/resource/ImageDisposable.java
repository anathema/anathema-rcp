package net.sf.anathema.basics.eclipse.resource;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.swt.graphics.Image;

public class ImageDisposable implements IDisposable {

  private final Image image;

  public ImageDisposable(Image image) {
    this.image = image;
  }

  @Override
  public void dispose() {
    image.dispose();
  }
}