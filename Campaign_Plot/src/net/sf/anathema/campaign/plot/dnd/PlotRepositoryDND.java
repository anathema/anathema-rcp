package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.basics.repository.IRepositoryDND;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.Transfer;

public class PlotRepositoryDND implements IRepositoryDND {

  private final int dndOperations = DND.DROP_MOVE;
  private final Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };

  @Override
  public void initDragAndDrop(final TreeViewer viewer) {
    DragSource source = new DragSource(viewer.getControl(), dndOperations);
    source.setTransfer(transfers);
    source.addDragListener(new PlotDragSourceListener(viewer));
    viewer.addDropSupport(dndOperations, transfers, new PlotDropListener(viewer));
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}