package net.sf.anathema.campaign.conflictweb;

import net.sf.anathema.campaign.conflictweb.model.ConflictWeb;
import net.sf.anathema.campaign.conflictweb.parts.ConflictWebEditpartFactory;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;

public class ConflictWebEditor extends GraphicalEditor {
  private ConflictWeb conflictWeb;

  public ConflictWebEditor() {
    setEditDomain(new DefaultEditDomain(this));
  }

  @Override
  protected void configureGraphicalViewer() {
    super.configureGraphicalViewer();
    GraphicalViewer viewer = getGraphicalViewer();
    viewer.setEditPartFactory(new ConflictWebEditpartFactory());
    viewer.setRootEditPart(new ScalableFreeformRootEditPart());
    ContextMenuProvider cmProvider = new ConflictWebContextMenuProvider(viewer, getActionRegistry());
    viewer.setContextMenu(cmProvider);
    getSite().registerContextMenu(cmProvider, viewer);
  }

  @Override
  protected void initializeGraphicalViewer() {
    conflictWeb = new ConflictWeb();
    getGraphicalViewer().setContents(conflictWeb);
  }

  @Override
  public void doSave(IProgressMonitor monitor) {
    throw new UnsupportedOperationException("No save allowed"); //$NON-NLS-1$
  }
}