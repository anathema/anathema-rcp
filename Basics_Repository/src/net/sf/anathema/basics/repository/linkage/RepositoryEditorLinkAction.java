package net.sf.anathema.basics.repository.linkage;

import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;

public final class RepositoryEditorLinkAction extends Action {
  private final IResourceSelectable repositoryView;
  private final TopPartListener topPartListener;

  public RepositoryEditorLinkAction(IWorkbenchWindow workbenchWindow, IResourceSelectable repositoryView) {
    super(Messages.RepositoryEditorLinkAction_LinkWithEditor, IAction.AS_CHECK_BOX);
    setImageDescriptor(RepositoryPlugin.getDefaultInstance().getImageDescriptor("icons/synced.gif")); //$NON-NLS-1$
    this.repositoryView = repositoryView;
    topPartListener = new TopPartListener(this);
    workbenchWindow.getPartService().addPartListener(topPartListener);
  }

  @Override
  public void run() {
    updateLink();
  }

  private void updateLink() {
    if (!isChecked()) {
      return;
    }
    IEditorPart topPart = topPartListener.getTopPart();
    if (topPart == null) {
      return;
    }
    IEditorInput editorInput = topPart.getEditorInput();
    IResource resource = (IResource) editorInput.getAdapter(IResource.class);
    repositoryView.setSelection(resource);
  }
}