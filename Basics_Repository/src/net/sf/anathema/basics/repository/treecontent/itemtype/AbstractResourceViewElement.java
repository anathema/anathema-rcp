package net.sf.anathema.basics.repository.treecontent.itemtype;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.input.internal.FileItemEditorInput;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractResourceViewElement implements IViewElement {

  private final String untitledName;
  private final IViewElement parent;
  private final IPrintNameProvider printNameProvider;

  public AbstractResourceViewElement(String untitledName, IViewElement parent, IPrintNameProvider printNameProvider) {
    this.untitledName = untitledName;
    this.parent = parent;
    this.printNameProvider = printNameProvider;
  }

  @Override
  public final String getDisplayName() {
    String printName = printNameProvider.getPrintName(getEditFile());
    if (StringUtilities.isNullOrTrimEmpty(printName)) {
      return untitledName;
    }
    return printName;
  }

  @Override
  public final void openEditor(IWorkbenchPage page) throws PartInitException {
    IFile file = getEditFile();
    try {
      IEditorInput input = new FileItemEditorInput(file, untitledName, getImageDescriptor());
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

  @Override
  public final IViewElement getParent() {
    return parent;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return parent.getImageDescriptor();
  }

  protected abstract IFile getEditFile();

  @Override
  public final boolean equals(Object object) {
    if (!(object instanceof AbstractResourceViewElement)) {
      return false;
    }
    AbstractResourceViewElement other = (AbstractResourceViewElement) object;
    return ObjectUtilities.equals(getEditFile(), other.getEditFile())
        && ObjectUtilities.equals(getParent(), other.getParent());
  }

  @Override
  public int hashCode() {
    return getEditFile().hashCode();
  }

  @Override
  public Object getAdapter(Class adapter) {
    if (adapter == IResource.class) {
      return getEditFile();
    }
    if (adapter == IPageDelible.class) {
      return createDelible();
    }
    return null;
  }

  protected abstract IPageDelible createDelible();
}