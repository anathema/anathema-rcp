package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.net.URL;

import net.sf.anathema.basics.eclipse.ui.IEditorOpener;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.input.internal.FileItemEditorInput;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ResourceEditorOpener implements IEditorOpener {

  private final IFile file;
  private final String untitledName;
  private final URL imageUrl;

  public ResourceEditorOpener(IFile file, String untitledName, URL imageUrl) {
    this.file = file;
    this.untitledName = untitledName;
    this.imageUrl = imageUrl;
  }

  @Override
  public final void openEditor(IWorkbenchPage page) throws PartInitException {
    try {
      IEditorInput input = createEditorInput();
      String fileName = file.getName();
      IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(fileName);
      page.openEditor(input, defaultEditor.getId());
    }
    catch (PersistenceException e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          RepositoryPlugin.ID,
          BasicRepositoryMessages.RepositoryBasics_CreateEditorInputFailedMessage,
          e));
    }
    catch (CoreException e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          RepositoryPlugin.ID,
          BasicRepositoryMessages.RepositoryBasics_CreateEditorInputFailedMessage,
          e));
    }
  }

  public IEditorInput createEditorInput() throws PersistenceException, CoreException {
    return new FileItemEditorInput(file, untitledName, imageUrl);
  }
}