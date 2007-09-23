package net.sf.anathema.basics.jface.resource;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.swt.graphics.Image;

public class MarkerDecoratedImageProvider implements IDisposable {

  private final String markerId;
  private final ImageDescriptor decoration;
  private final int decorationPosition;
  private final Map<Object, Image> basicImages = new HashMap<Object, Image>();
  private final Map<Object, Image> decoratedImages = new HashMap<Object, Image>();

  public MarkerDecoratedImageProvider(String markerId, ImageDescriptor decoration, int decorationPosition) {
    this.markerId = markerId;
    this.decoration = decoration;
    this.decorationPosition = decorationPosition;
  }

  public Image getImage(IImagedAdaptable adaptable) {
    Image image = decoratedImages.get(adaptable);
    if (image != null) {
      image.dispose();
    }
    Image basicImage = getBasicImage(adaptable);
    IResource resource = (IResource) adaptable.getAdapter(IResource.class);
    if (resource == null || !resource.exists()) {
      return basicImage;
    }
    try {
      IMarker[] foundMarkers = resource.findMarkers(markerId, true, IResource.DEPTH_ONE);
      if (foundMarkers.length == 0) {
        return basicImage;
      }
      Image decoratedImage = new DecorationOverlayIcon(basicImage, decoration, decorationPosition).createImage();
      decoratedImages.put(adaptable, decoratedImage);
      return decoratedImage;
    }
    catch (CoreException e) {
      new Logger("net.sf.anathema.basics.jface").error(Messages.MarkerDecoratedImageProvider_ErrorMessageMarkerSearch, e); //$NON-NLS-1$
      return basicImage;
    }
  }

  private Image getBasicImage(IImagedAdaptable adaptable) {
    Image basicImage = basicImages.get(adaptable);
    if (basicImage != null) {
      return basicImage;
    }
    basicImage = adaptable.getImageDescriptor().createImage();
    basicImages.put(adaptable, basicImage);
    return basicImage;
  }

  private void clearImageMap(Map<Object, Image> imageList) {
    for (Image image : imageList.values()) {
      image.dispose();
    }
    imageList.clear();
  }

  @Override
  public void dispose() {
    clearImageMap(basicImages);
    clearImageMap(decoratedImages);
  }
}