package net.sf.anathema.basics.repository.problems;

import net.sf.anathema.basics.repository.view.internal.TreeViewRefresher;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.ViewPart;

public class ProblemsView extends ViewPart {

  private TreeViewer viewer;
  private final AggregatedDisposable disposables = new AggregatedDisposable();

  @Override
  public void createPartControl(Composite parent) {
    setPartName("Problems");
    Tree tree = new Tree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    tree.setLinesVisible(true);
    tree.setHeaderVisible(true);
    createColumn(tree, "Description");
    createColumn(tree, "Path");
    this.viewer = new TreeViewer(tree);
    final TreeViewRefresher treeViewRefresher = new TreeViewRefresher(viewer, parent.getDisplay());
    treeViewRefresher.setRule(ResourcesPlugin.getWorkspace().getRoot());
    final IResourceChangeListener resourceListener = new IResourceChangeListener() {
      @Override
      public void resourceChanged(IResourceChangeEvent event) {
        treeViewRefresher.schedule();
      }
    };
    ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
    disposables.addDisposable(new IDisposable() {
      @Override
      public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceListener);
      }
    });
    viewer.setContentProvider(new ProblemsContentProvider());
    viewer.setLabelProvider(new ProblemsLabelProvider());
    viewer.setInput(getViewSite());
  }

  @Override
  public void dispose() {
    disposables.dispose();
    super.dispose();
  }

  private void createColumn(Tree tree, String header) {
    TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
    treeColumn.setResizable(true);
    treeColumn.setText(header);
    treeColumn.setWidth(450);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}