package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.jface.FileEditorInput;

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

  public ResourceViewElement(IResource resource, IViewElement parent) {
    this.resource = resource;
    this.parent = parent;
  }

  @Override
  public Object[] getChildren() {
    return new Object[0];
  }

  @Override
  public String getDisplayName() {
    return resource.getName();
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
  public void openEditor(IWorkbenchPage page) {
    IFile file = (IFile) resource;
    IEditorInput input = new FileEditorInput(file);
    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
    try {
      page.openEditor(input, defaultEditor.getId());
    }
    catch (PartInitException e) {
      e.printStackTrace();
    }
  }
}