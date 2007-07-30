package net.sf.anathema.basics.repository.linkage;

import net.sf.anathema.basics.eclipse.ui.PartContainer;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;

public final class ViewEditorLinker implements IDisposable, Runnable, IViewEditorLinker {
  private final IResourceSelector selector;
  private final TopPartListener topPartListener;
  private boolean enabled;
  private final AggregatedDisposable disposables = new AggregatedDisposable();
  private final PartContainer partContainer;

  public ViewEditorLinker(IWorkbenchWindow workbenchWindow, IResourceSelector selector) {
    this.selector = selector;
    topPartListener = new TopPartListener(this);
    partContainer = new PartContainer(workbenchWindow);
    disposables.addDisposable(new PartListening(topPartListener, partContainer));
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
    IEditorInput editorInput = partContainer.getEditorInput();
    if (editorInput == null) {
      selector.setSelection(null);
    }
    else {
      IResource resource = (IResource) editorInput.getAdapter(IResource.class);
      selector.setSelection(resource);
    }
  }

  @Override
  public void dispose() {
    disposables.dispose();
  }
}