package net.sf.anathema.charms.character.editor.dnd;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;

public final class CharmDragListener extends DragSourceAdapter {
  private final TableViewer availableTable;

  public CharmDragListener(TableViewer availableTable) {
    this.availableTable = availableTable;
  }

  @Override
  public void dragSetData(DragSourceEvent event) {
    DragSource dragSource = (DragSource) event.widget;
    LocalSelectionTransfer transfer = (LocalSelectionTransfer) dragSource.getTransfer()[0];
    transfer.setSelection(availableTable.getSelection());
  }
}