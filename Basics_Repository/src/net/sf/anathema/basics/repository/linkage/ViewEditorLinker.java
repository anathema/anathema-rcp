package net.sf.anathema.basics.repository.linkage;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchWindow;

public final class ViewEditorLinker implements IDisposable, Runnable, IViewEditorLinker {
  private final IResourceSelector selector;
  private final TopPartListener topPartListener;
  private final IPartService partService;
  private boolean enabled;

  public ViewEditorLinker(IWorkbenchWindow workbenchWindow, IResourceSelector selector) {
    this.selector = selector;
    topPartListener = new TopPartListener(this);
    partService = workbenchWindow.getPartService();
    partService.addPartListener(topPartListener);
  }

  public void setLinkEnabled(boolean enabled) {
    this.enabled = enabled;
    if (enabled) {
      updateLink();
    }
  }

  @Override
  public void run() {
    updateLink();
  }

  private void updateLink() {
    if (!enabled) {
      return;
    }
    IEditorPart topPart = topPartListener.getTopPart();
    if (topPart == null) {
      return;
    }
    IEditorInput editorInput = topPart.getEditorInput();
    IResource resource = (IResource) editorInput.getAdapter(IResource.class);
    selector.setSelection(resource);
  }

  @Override
  public void dispose() {
    partService.removePartListener(topPartListener);
  }
}