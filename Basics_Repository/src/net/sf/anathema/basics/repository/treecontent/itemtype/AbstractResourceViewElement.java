package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.net.URL;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

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
    if (StringUtilities.isNullOrTrimmedEmpty(printName)) {
      return untitledName;
    }
    return printName;
  }

  @Override
  public final void openEditor(IWorkbenchPage page) throws PartInitException {
    createEditorOpener().openEditor(page);
  }

  private IEditorInput createEditorInput() throws PersistenceException, CoreException {
    return createEditorOpener().createEditorInput();
  }

  private ResourceEditorOpener createEditorOpener() {
    return new ResourceEditorOpener(getEditFile(), untitledName, getImageUrl());
  }

  @Override
  public final IViewElement getParent() {
    return parent;
  }

  @Override
  public URL getImageUrl() {
    return parent.getImageUrl();
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

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    if (adapter == IResource.class) {
      return getEditFile();
    }
    if (adapter == IPageDelible.class) {
      return createDelible();
    }
    if (adapter == IEditorInputProvider.class) {
      return new IEditorInputProvider() {
        @Override
        public IEditorInput getEditorInput() throws PersistenceException, CoreException {
          return AbstractResourceViewElement.this.createEditorInput();
        }
      };
    }
    return null;
  }

  protected abstract IPageDelible createDelible();
}