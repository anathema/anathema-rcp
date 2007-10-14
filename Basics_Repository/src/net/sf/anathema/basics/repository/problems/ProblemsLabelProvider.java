package net.sf.anathema.basics.repository.problems;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ProblemsLabelProvider extends LabelProvider implements ITableLabelProvider {

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    return null;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    IMarker marker = (IMarker) element;
    if (columnIndex == 0) {
      return String.valueOf(marker.getAttribute("description", "No description given."));
    }
    if (columnIndex == 1) {
      return String.valueOf(marker.getAttribute("path", "No path defined."));
    }
    throw new IllegalArgumentException("Unsupported column index: " + columnIndex);
  }
}