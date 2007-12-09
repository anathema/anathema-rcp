package net.sf.anathema.campaign.conflictweb;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.GraphicalEditor;

public class ConflictWebEditor extends GraphicalEditor {
  public ConflictWebEditor() {
    setEditDomain(new DefaultEditDomain(this));
  }

  @Override
  protected void configureGraphicalViewer() {
    super.configureGraphicalViewer();
    getGraphicalViewer().setEditPartFactory(new ConflictWebEditpartFactory());
  }

  @Override
  protected void initializeGraphicalViewer() {
    // nothing to do
  }

  @Override
  public void doSave(IProgressMonitor monitor) {
    throw new UnsupportedOperationException("No save allowed"); //$NON-NLS-1$
  }
}