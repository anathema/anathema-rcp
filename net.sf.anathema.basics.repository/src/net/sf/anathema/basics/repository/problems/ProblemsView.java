package net.sf.anathema.basics.repository.problems;

import net.sf.anathema.basics.repository.refresh.ColumnViewRefresher;
import net.sf.anathema.basics.repository.refresh.ResourceChangeJobScheduler;
import net.sf.anathema.lib.ui.AggregatedDisposable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class ProblemsView extends ViewPart {

  private TableViewer viewer;
  private final AggregatedDisposable disposables = new AggregatedDisposable();

  @Override
  public void createPartControl(Composite parent) {
    setPartName(Messages.ProblemsView_Title);
    Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
    table.setLinesVisible(true);
    table.setHeaderVisible(true);
    createColumn(table, Messages.ProblemsView_DescriptionHeader);
    createColumn(table, Messages.ProblemsView_PathHeader);
    this.viewer = new TableViewer(table);
    disposables.addDisposable(new ResourceChangeJobScheduler(new ColumnViewRefresher(viewer, parent.getDisplay())));
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

  private void createColumn(Table table, String header) {
    TableColumn column = new TableColumn(table, SWT.NONE);
    column.setResizable(true);
    column.setText(header);
    column.setWidth(450);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}