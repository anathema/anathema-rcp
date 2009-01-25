package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public class PartContainer implements IPartContainer {

  private final IPartService partService;
  private final IWorkbenchWindow workbenchWindow;

  public PartContainer(IWorkbenchWindow workbenchWindow) {
    this.workbenchWindow = workbenchWindow;
    this.partService = workbenchWindow.getPartService();
  }

  @Override
  public void addPartListener(IPartListener listener) {
    partService.addPartListener(listener);
  }

  @Override
  public void removePartListener(IPartListener listener) {
    partService.removePartListener(listener);
  }

  public IEditorInput getEditorInput() {
    IWorkbenchPage activePage = workbenchWindow.getActivePage();
    if (noEditorIsActive(activePage)) {
      return new NullEditorInput();
    }
    return activePage.getActiveEditor().getEditorInput();
  }

  private boolean noEditorIsActive(IWorkbenchPage activePage) {
    return activePage == null || activePage.getActiveEditor() == null;
  }
}