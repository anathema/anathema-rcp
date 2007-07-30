package net.sf.anathema.basics.eclipse.resource;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class FileContentHandle implements IContentHandle {

  private final IFile file;

  public FileContentHandle(IFile file) {
    this.file = file;
  }

  @Override
  public InputStream getContents() throws CoreException {
    return file.getContents();
  }

  @Override
  public boolean exists() {
    return file.exists();
  }

  @Override
  public void setContents(InputStream source, IProgressMonitor monitor) throws CoreException {
    if (file.exists()) {
      file.setContents(source, true, true, monitor);
    }
    else {
      file.create(source, true, monitor);
    }
  }
}