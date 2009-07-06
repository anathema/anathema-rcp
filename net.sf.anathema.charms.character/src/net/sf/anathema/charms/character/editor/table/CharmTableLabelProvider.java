package net.sf.anathema.charms.character.editor.table;


import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class CharmTableLabelProvider extends LabelProvider implements ITableLabelProvider {

  private final ITableColumn[] columns;

  public CharmTableLabelProvider(ITableColumn... columns) {
    this.columns = columns;
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    return columns[columnIndex].getImage(element);
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    return columns[columnIndex].getText(element);
  }
}