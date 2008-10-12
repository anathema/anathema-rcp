package net.sf.anathema.charms.character;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.model.IConfigurableViewElement;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharmTreeViewElement implements IViewElement {

  private final IConfigurableViewElement parent;
  private final String treeId;

  public CharmTreeViewElement(IConfigurableViewElement parent, String treeId) {
    this.parent = parent;
    this.treeId = treeId;
  }

  @Override
  public IViewElement[] getChildren() {
    return new IViewElement[0];
  }

  @Override
  public IViewElement getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public URL getImageUrl() {
    return parent.getImageUrl();
  }

  @Override
  public Object getAdapter(Class adapter) {
    if (adapter == IEditorInputProvider.class) {
      final IEditorInputProvider inputProvider = (IEditorInputProvider) parent.getAdapter(adapter);
      return new IEditorInputProvider() {
        @Override
        public IEditorInput getEditorInput() throws PersistenceException, CoreException, ExtensionException {
          CharmsEditorInput editorInput = (CharmsEditorInput) inputProvider.getEditorInput();
          editorInput.setTreeId(treeId);
          return editorInput;
        }
      };
    }
    return parent.getAdapter(adapter);
  }

  @Override
  public String getDisplayName() {
    return treeId;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    IEditorInputProvider factory = (IEditorInputProvider) getAdapter(IEditorInputProvider.class);
    IEditorInput editorInput = null;
    try {
      editorInput = factory.getEditorInput();
    }
    catch (Exception e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          IPluginConstants.PLUGIN_ID,
          BasicRepositoryMessages.RepositoryBasics_CreateEditorInputFailedMessage,
          e));
    }
    parent.openEditorForChild(page, editorInput);
  }
}