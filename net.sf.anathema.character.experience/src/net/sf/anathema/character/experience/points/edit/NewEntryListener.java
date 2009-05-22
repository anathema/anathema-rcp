package net.sf.anathema.character.experience.points.edit;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

public final class NewEntryListener extends MouseAdapter {
  private final Text inputText;
  private final TableViewer tableViewer;

  public NewEntryListener(Text inputText, TableViewer tableViewer) {
    this.inputText = inputText;
    this.tableViewer = tableViewer;
  }

  @Override
  public void mouseDoubleClick(MouseEvent e) {
    ViewerCell cell = tableViewer.getCell(new Point(e.x, e.y));
    boolean newEntry = cell == null;
    if (newEntry) {
      inputText.setFocus();
    }
  }
}