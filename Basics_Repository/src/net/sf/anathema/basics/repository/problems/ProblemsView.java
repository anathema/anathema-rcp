package net.sf.anathema.basics.repository.problems;

import net.sf.anathema.basics.repository.refresh.ResourceChangeTreeRefresher;
import net.sf.anathema.lib.ui.AggregatedDisposable;

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
    setPartName(Messages.ProblemsView_Title);
    Tree tree = new Tree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    tree.setLinesVisible(true);
    tree.setHeaderVisible(true);
    createColumn(tree, Messages.ProblemsView_DescriptionHeader);
    createColumn(tree, Messages.ProblemsView_PathHeader);
    this.viewer = new TreeViewer(tree);
    disposables.addDisposable(new ResourceChangeTreeRefresher(viewer, parent.getDisplay()));
    viewer.setContentProvider(new ProblemsContentProvider());
    viewer.setLabelProvider(new ProblemsLabelProvider());
    viewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
    viewer.addOpenListener(new ProblemOpenListener());
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