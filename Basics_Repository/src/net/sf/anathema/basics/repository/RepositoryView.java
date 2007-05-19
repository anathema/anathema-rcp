package net.sf.anathema.basics.repository;

import net.sf.anathema.basics.jface.context.ContextMenuManager;
import net.sf.anathema.basics.repository.treecontent.IViewElement;
import net.sf.anathema.basics.repository.treecontent.RepositoryLabelProvider;
import net.sf.anathema.basics.repository.treecontent.TypedTreeContentProvider;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class RepositoryView extends ViewPart {
  public static final String ID = "net.sf.anathema.basics.repositoryview"; //$NON-NLS-1$

  private TreeViewer viewer;

  @Override
  public void createPartControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    ContextMenuManager.connect(getSite(), viewer);
    viewer.setContentProvider(new TypedTreeContentProvider());
    viewer.setLabelProvider(new RepositoryLabelProvider());
    viewer.setInput(getViewSite());
    viewer.addOpenListener(new IOpenListener() {
      public void open(OpenEvent event) {
        StructuredSelection selection = (StructuredSelection) event.getSelection();
        IViewElement element = (IViewElement) selection.getFirstElement();
        try {
          element.openEditor(getViewSite().getPage());
        }
        catch (PartInitException e) {
          RepositoryPlugin.log(IStatus.ERROR, "Couldn't open editor.", e);
        }
      }
    });
    viewer.refresh(true);
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }
  
  public void updateRepositoryTree() {
    viewer.refresh(true);
  }
}