package net.sf.anathema.character.experience.points;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public final class ExperiencePointsLabelProvider extends LabelProvider implements ITableLabelProvider {
  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    return null;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    ExperienceEntry entry = (ExperienceEntry) element;
    if (columnIndex == 0) {
      return String.valueOf(entry.points);
    }
    return entry.comment;
  }
}