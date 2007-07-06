package net.sf.anathema.campaign.plot.repository;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;

public class DeleteIconCompositeImageDescriptor extends ImageDescriptor {

  private final ImageDescriptor descriptor;

  public DeleteIconCompositeImageDescriptor(ImageDescriptor descriptor) {
    this.descriptor = descriptor;
  }

  @Override
  public ImageData getImageData() {
    // TODO Overlay mit Lösch-Kreuz
    return descriptor.getImageData();
  }
}