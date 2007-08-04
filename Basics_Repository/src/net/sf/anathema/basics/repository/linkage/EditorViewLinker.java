package net.sf.anathema.basics.repository.linkage;

import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public final class EditorViewLinker {

  private final IWorkbenchWindow workbenchWindow;
  private final ISelectionProvider provider;
  private boolean enabled;

  public EditorViewLinker(IWorkbenchWindow workbenchWindow, ISelectionProvider provider) {
    this.workbenchWindow = workbenchWindow;
    this.provider = provider;
  }

  public void update() {
    if (!enabled) {
      return;
    }
    IWorkbenchPage page = workbenchWindow.getActivePage();
    if (page == null) {
      return;
    }
    StructuredSelection selection = (StructuredSelection) provider.getSelection();
    if (selection.isEmpty()) {
      return;
    }
    IViewElement element = (IViewElement) selection.getFirstElement();
    IEditorInputProvider inputProvider = (IEditorInputProvider) element.getAdapter(IEditorInputProvider.class);
    if (inputProvider == null) {
      return;
    }
    IEditorInput editorInput;
    try {
      editorInput = inputProvider.getEditorInput();
      page.bringToTop(page.findEditor(editorInput));
    }
    catch (Exception e) {
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, "Could not bring editor to top.", e);
    }
  }

  public void setLinkEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}