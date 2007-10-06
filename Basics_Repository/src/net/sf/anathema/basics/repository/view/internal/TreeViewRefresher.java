package net.sf.anathema.basics.repository.view.internal;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

public class TreeViewRefresher extends Job {

  private final TreeViewer viewer;
  private final Display display;

  public TreeViewRefresher(TreeViewer viewer, Display display) {
    super(Messages.TreeViewRefresher_JobName);
    this.viewer = viewer;
    this.display = display;
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
    if (viewer.getTree().isDisposed()) {
      return;
    }
    Object[] expandedElements = viewer.getExpandedElements();
    viewer.refresh(true);
    viewer.setExpandedElements(expandedElements);
  }
}