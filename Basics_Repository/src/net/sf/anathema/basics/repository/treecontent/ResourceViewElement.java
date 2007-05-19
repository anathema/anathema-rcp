package net.sf.anathema.basics.repository.treecontent;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.repository.input.FileItemEditorInput;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ResourceViewElement implements IViewElement {
  private final IResource resource;
  private final IViewElement parent;
  private final IPrintNameProvider printNameProvider;

  public ResourceViewElement(IResource resource, IPrintNameProvider printNameProvider, IViewElement parent) {
    this.resource = resource;
    this.parent = parent;
    this.printNameProvider = printNameProvider;
  }

  @Override
  public Object[] getChildren() {
    return new Object[0];
  }

  @Override
  public String getDisplayName() {
    return printNameProvider.getPrintName((IFile) resource);
  }

  @Override
  public Image getImage() {
    return parent.getImage();
  }

  @Override
  public Object getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    IFile file = (IFile) resource;
    IEditorInput input = new FileItemEditorInput(file);
    String fileName = file.getName();
    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(fileName);
    page.openEditor(input, defaultEditor.getId());
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof ResourceViewElement)) {
      return false;
    }
    ResourceViewElement other = (ResourceViewElement) object;
    return ObjectUtilities.equals(resource, other.resource) && ObjectUtilities.equals(parent, other.parent);
  }

  @Override
  public int hashCode() {
    return resource.hashCode();
  }
}