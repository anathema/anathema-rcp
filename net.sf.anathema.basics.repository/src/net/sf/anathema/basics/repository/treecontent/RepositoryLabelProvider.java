package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.jface.resource.IImagedAdaptable;
import net.sf.anathema.basics.jface.resource.MarkerDecoratedImageProvider;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class RepositoryLabelProvider extends LabelProvider implements ITableLabelProvider {

  private static final String MARKER_VIEW_ELEMENT = "net.sf.anathema.markers.view.element"; //$NON-NLS-1$
  private MarkerDecoratedImageProvider imageProvider = new MarkerDecoratedImageProvider(
      MARKER_VIEW_ELEMENT,
      RepositoryPlugin.getDefaultInstance().getImageRegistry().getDescriptor(RepositoryPlugin.WARNING_DECORATION),
      IDecoration.BOTTOM_LEFT);

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
    return imageProvider.getImage((IImagedAdaptable) obj);
  }

  private IViewElement cast(Object element) {
    return (IViewElement) element;
  }

  @Override
  public void dispose() {
    imageProvider.dispose();
    super.dispose();
  }
}