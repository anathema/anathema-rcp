package net.sf.anathema.basics.repository.problems;

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
    IProblem problem = (IProblem) element;
    if (columnIndex == 0) {
      return problem.getDescription();
    }
    if (columnIndex == 1) {
      return problem.getPath();
    }
    throw new IndexOutOfBoundsException("Unsupported column index: " + columnIndex); //$NON-NLS-1$
  }
}