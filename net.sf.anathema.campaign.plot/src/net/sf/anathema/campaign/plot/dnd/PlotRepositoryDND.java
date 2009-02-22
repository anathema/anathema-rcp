package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.repository.view.IRepositoryDND;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IViewSite;

public class PlotRepositoryDND extends UnconfiguredExecutableExtension implements IRepositoryDND {

  private final int dndOperations = DND.DROP_MOVE;
  private final Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };

  @Override
  public void initDragAndDrop(final TreeViewer viewer, IViewSite site) {
    DragSource source = new DragSource(viewer.getControl(), dndOperations);
    source.setTransfer(transfers);
    source.addDragListener(new PlotDragSourceListener(viewer));
    viewer.addDropSupport(dndOperations, transfers, new PlotDropListener(viewer, site));
  }
}