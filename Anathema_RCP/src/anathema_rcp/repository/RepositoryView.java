package anathema_rcp.repository;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import editor.styledtext.editors.FileEditorInput;

public class RepositoryView extends ViewPart {
  public static final String ID = "Anathema_RCP.view";

  private TreeViewer viewer;

  @Override
  public void createPartControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new RepositoryContentProvider());
    viewer.setLabelProvider(new RepositoryLabelProvider());
    viewer.setInput(getViewSite());
    viewer.addOpenListener(new IOpenListener() {
      public void open(OpenEvent event) {
        StructuredSelection selection = (StructuredSelection) event.getSelection();
        Object firstElement = selection.getFirstElement();
        if (firstElement instanceof IFile) {
          IFile file = (IFile) firstElement;
          IEditorInput input = new FileEditorInput(file);
          IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(
              file.getName());
          try {
            getViewSite().getPage().openEditor(input, defaultEditor.getId());
          }
          catch (PartInitException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }
}