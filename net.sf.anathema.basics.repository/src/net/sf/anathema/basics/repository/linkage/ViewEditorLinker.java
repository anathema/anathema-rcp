package net.sf.anathema.basics.repository.linkage;

import net.sf.anathema.basics.eclipse.ui.PartContainer;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;

public final class ViewEditorLinker implements IDisposable, Runnable, ILinker {
  private final ILinkageSelector selector;
  private final TopPartListener topPartListener;
  private boolean enabled;
  private final AggregatedDisposable disposables = new AggregatedDisposable();
  private final PartContainer partContainer;

  public ViewEditorLinker(IWorkbenchWindow workbenchWindow, ILinkageSelector selector) {
    this.selector = selector;
    topPartListener = new TopPartListener(this);
    partContainer = new PartContainer(workbenchWindow);
    disposables.addDisposable(new PartListening(topPartListener, partContainer));
  }

  @Override
  public void toggleLink() {
    this.enabled = !enabled;
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
    ILink link = (ILink) editorInput.getAdapter(ILink.class);
    selector.setSelection(link);
  }

  @Override
  public void dispose() {
    disposables.dispose();
  }
}