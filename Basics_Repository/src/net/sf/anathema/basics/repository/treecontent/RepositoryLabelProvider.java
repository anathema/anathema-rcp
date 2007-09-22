package net.sf.anathema.basics.repository.treecontent;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class RepositoryLabelProvider extends LabelProvider implements ITableLabelProvider {

  private static final String MARKER_VIEW_ELEMENT = "net.sf.anathema.markers.view.element"; //$NON-NLS-1$
  private Map<Object, Image> images = new HashMap<Object, Image>();

  @Override
  public String getText(Object element) {
    return cast(element).getDisplayName();
  }

  @Override
  public String getColumnText(Object obj, int index) {
    return getText(obj);
  }

  @Override
  public Image getColumnImage(Object obj, int index) {
    return getImage(obj);
  }

  @Override
  public Image getImage(Object obj) {
    Image basicImage = getBasicImage(obj);
    IResource resource = (IResource) cast(obj).getAdapter(IResource.class);
    if (resource == null) {
      return basicImage;
    }
    try {
      IMarker[] foundMarkers = resource.findMarkers(MARKER_VIEW_ELEMENT, true, IResource.DEPTH_ONE);
      if (foundMarkers.length == 0) {
        return basicImage;
      }
      ImageDescriptor warningImage = RepositoryPlugin.getDefaultInstance().getImageRegistry().getDescriptor(
          RepositoryPlugin.WARNING_DECORATION);
      DecorationOverlayIcon warningDescriptor = new DecorationOverlayIcon(
          basicImage,
          warningImage,
          IDecoration.BOTTOM_LEFT);
      return warningDescriptor.createImage();
    }
    catch (CoreException e) {
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, "Error searching markers", e);
      return basicImage;
    }
  }

  private Image getBasicImage(Object obj) {
    if (images.get(obj) != null) {
      return images.get(obj);
    }
    IViewElement viewElement = cast(obj);
    Image image = viewElement.getImageDescriptor().createImage();
    images.put(obj, image);
    return image;
  }

  private IViewElement cast(Object element) {
    return (IViewElement) element;
  }

  @Override
  public void dispose() {
    for (Image image : images.values()) {
      image.dispose();
    }
    images.clear();
    super.dispose();
  }
}