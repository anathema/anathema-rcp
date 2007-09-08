package net.sf.anathema.basics.repository.view.internal;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class TreeViewRefresherTest {

  @Test
  public void doesNotRefreshViewerWhenDisplayDisposed() throws Exception {
    Shell shell = new org.eclipse.swt.widgets.Shell();
    TreeViewRefresher refresher = new TreeViewRefresher(new TreeViewer(shell), shell.getDisplay());
    shell.dispose();
    refresher.run(new NullProgressMonitor());
  }
}