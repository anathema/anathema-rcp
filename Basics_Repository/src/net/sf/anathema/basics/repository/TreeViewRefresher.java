package net.sf.anathema.basics.repository;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

public class TreeViewRefresher extends Job {

  private final TreeViewer viewer;
  private final Display display;

  /** Constructor has to be called from display thread to get the correct Display for execution. */
  public TreeViewRefresher(TreeViewer viewer) {
    super("Refresh Repository View"); //$NON-NLS-1$
    this.viewer = viewer;
    this.display = Display.getCurrent();
  }

  @Override
  protected IStatus run(IProgressMonitor monitor) {
    if (display == null) {
      return Status.CANCEL_STATUS;
    }
    display.syncExec(new Runnable() {
      @Override
      public void run() {
        refresh();
      }
    });
    return Status.OK_STATUS;
  }

  private void refresh() {
    Object[] expandedElements = viewer.getExpandedElements();
    viewer.refresh(true);
    viewer.setExpandedElements(expandedElements);
  }
}