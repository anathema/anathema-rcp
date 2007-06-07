package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public class NewIconCompositeImageDescriptor extends CompositeImageDescriptor {
  private final ImageDescriptor image;

  public NewIconCompositeImageDescriptor(ImageDescriptor image) {
    this.image = image;
  }

  @Override
  protected void drawCompositeImage(int width, int height) {
    drawImage(image.getImageData(), 0, 0);
    String resourcePath = "icons/OverlayAdd12.png"; //$NON-NLS-1$
    drawImage(PlotPlugin.getImageDescriptor(resourcePath).getImageData(), 4, 0);
  }

  @Override
  protected Point getSize() {
    ImageData data = image.getImageData();
    return new Point(data.width, data.height);
  }
}