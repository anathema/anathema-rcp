package net.sf.anathema.charms.character.editor.dnd;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;

public final class CharmTableDragListener extends DragSourceAdapter {
  private final TableViewer table;

  public CharmTableDragListener(TableViewer table) {
    this.table = table;
  }

  @Override
  public void dragSetData(DragSourceEvent event) {
    DragSource dragSource = (DragSource) event.widget;
    LocalSelectionTransfer transfer = (LocalSelectionTransfer) dragSource.getTransfer()[0];
    transfer.setSelection(table.getSelection());
  }
}