package net.sf.anathema.basics.repository.treecontent.itemtype;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.repository.input.FileItemEditorInput;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
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
    IEditorInput input = new FileItemEditorInput(file, untitledName, ImageDescriptor.createFromImage(getImage()));
    String fileName = file.getName();
    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(fileName);
    page.openEditor(input, defaultEditor.getId());
  }

  @Override
  public final IViewElement getParent() {
    return parent;
  }

  @Override
  public Image getImage() {
    return parent.getImage();
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
}