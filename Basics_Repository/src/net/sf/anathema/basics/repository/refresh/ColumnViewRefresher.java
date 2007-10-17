package net.sf.anathema.basics.repository.refresh;

import net.sf.anathema.basics.repository.view.internal.Messages;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Display;

public class ColumnViewRefresher extends Job {

  private final ColumnViewer viewer;
  private final Display display;

  public ColumnViewRefresher(ColumnViewer viewer, Display display) {
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
        if (viewer.getControl().isDisposed()) {
          return;
        }
        refresh();
      }
    });
    return Status.OK_STATUS;
  }

  protected void refresh() {
    viewer.refresh(true);
  }
}