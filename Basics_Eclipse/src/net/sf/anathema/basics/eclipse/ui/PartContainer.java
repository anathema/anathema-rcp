package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
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
    IEditorPart activeEditor = workbenchWindow.getActivePage().getActiveEditor();
    if (activeEditor == null) {
      return null;
    }
    return activeEditor.getEditorInput();
  }
}