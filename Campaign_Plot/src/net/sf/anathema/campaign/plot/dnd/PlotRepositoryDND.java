package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.basics.repository.IRepositoryDND;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Control;

public class PlotRepositoryDND implements IRepositoryDND {

  @Override
  public void initDragAndDrop(Control control) {
    DragSource source = new DragSource(control, DND.DROP_MOVE);
    source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
    source.addDragListener(new DragSourceAdapter() {

      @Override
      public void dragStart(DragSourceEvent event) {
        System.err.println("Drag Start");
        event.doit = true;
      }

      @Override
      public void dragSetData(DragSourceEvent event) {
        event.data = "Juchu"; //$NON-NLS-1$
      }
    });
    DropTarget dropTarget = new DropTarget(control, DND.DROP_MOVE);
    dropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance() });
    dropTarget.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        System.err.println(event.getSource() + ": " + event.data); //$NON-NLS-1$
      }
    });
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}