package net.sf.anathema.basics.jface;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class FileEditorInput implements IFileEditorInput {
  private IFile file;

  public FileEditorInput(IFile file) {
    if (file == null) {
      throw new IllegalArgumentException();
    }
    this.file = file;
  }

  @Override
  public int hashCode() {
    return file.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof IFileEditorInput)) {
      return false;
    }
    IFileEditorInput other = (IFileEditorInput) obj;
    return file.equals(other.getFile());
  }

  public boolean exists() {
    return file.exists();
  }

  public Object getAdapter(Class adapter) {
    if (adapter == IFile.class) {
      return file;
    }
    return file.getAdapter(adapter);
  }

  public IFile getFile() {
    return file;
  }

  public ImageDescriptor getImageDescriptor() {
    return null;
  }

  public String getName() {
    return file.getName();
  }

  public IPersistableElement getPersistable() {
    return null;
  }

  public IStorage getStorage() {
    return file;
  }

  public String getToolTipText() {
    return file.getFullPath().makeRelative().toString();
  }

  @Override
  public String toString() {
    return getClass().getName() + "(" + getFile().getFullPath() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}