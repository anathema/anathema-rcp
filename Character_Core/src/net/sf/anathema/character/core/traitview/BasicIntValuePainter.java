package net.sf.anathema.character.core.traitview;

import net.disy.commons.core.util.Ensure;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class BasicIntValuePainter implements IIntValuePainter {

  private final Image passiveImage;
  private final Image valueImage;

  public BasicIntValuePainter(Image passiveImage, Image valueImage) {
    this.passiveImage = passiveImage;
    this.valueImage = valueImage;
  }

  @Override
  public void drawImage(IIntValuePaintContext context, int index) {
    if (index < context.getValue()) {
      context.drawImage(index, valueImage);
    }
    else {
      context.drawImage(index, passiveImage);
    }
  }

  @Override
  public boolean isResponsable(IIntValuePaintContext context, int index) {
    return true;
  }

  @Override
  public void init(IRedrawable redrawable, int width, int height) {
    ImageData valueData = valueImage.getImageData();
    Ensure.ensureArgumentEquals(width, valueData.width);
    Ensure.ensureArgumentEquals(height, valueData.height);
    ImageData passiveData = passiveImage.getImageData();
    Ensure.ensureArgumentEquals(width, passiveData.width);
    Ensure.ensureArgumentEquals(height, passiveData.height);
  }
}