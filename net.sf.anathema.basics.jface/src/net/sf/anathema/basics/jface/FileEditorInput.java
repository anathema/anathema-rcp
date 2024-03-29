package net.sf.anathema.basics.jface;

import java.net.URL;

import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class FileEditorInput extends DefaultAdaptable implements IFileEditorInput {
  private final IFile file;
  private final ImageDescriptor imageDescriptor;
  private final URL imageUrl;

  public FileEditorInput(IFile file, URL imageUrl) {
    this.imageUrl = imageUrl;
    this.imageDescriptor = ImageDescriptor.createFromURL(imageUrl);
    if (file == null) {
      throw new IllegalArgumentException();
    }
    this.file = file;
    set(IResource.class, file);
    set(IFileEditorInput.class, this);
  }

  public final URL getImageUrl() {
    return imageUrl;
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

  @Override
  @SuppressWarnings("unchecked")
  public final Object getAdapter(Class adapter) {
    Object adaptedObject = super.getAdapter(adapter);
    if (adaptedObject != null) {
      return adaptedObject;
    }
    return file.getAdapter(adapter);
  }

  public final IFile getFile() {
    return file;
  }

  public ImageDescriptor getImageDescriptor() {
    return imageDescriptor;
  }

  public String getName() {
    return file.getName();
  }

  public IPersistableElement getPersistable() {
    if (!file.exists()) {
      return null;
    }
    return getPersistableInternal();
  }

  protected IPersistableElement getPersistableInternal() {
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