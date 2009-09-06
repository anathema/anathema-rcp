package net.sf.anathema.charms.character.editor.combo;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Control;

public class LocalDragAndDropControl {

  private final Transfer[] types = new Transfer[] { LocalSelectionTransfer.getTransfer() };

  public void addDropTarget(Control control, DropTargetListener listener) {
    DropTarget target = new DropTarget(control, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
    target.setTransfer(types);
    target.addDropListener(listener);
  }

  public void addDragSource(Control control, DragSourceListener listener) {
    DragSource source = new DragSource(control, DND.DROP_MOVE | DND.DROP_COPY);
    source.setTransfer(types);
    source.addDragListener(listener);
  }
}