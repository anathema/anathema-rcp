package net.sf.anathema.basics.eclipse.resource;

import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IContentHandle {

  public boolean exists();

  public InputStream getContents() throws CoreException;

  public void setContents(InputStream source, IProgressMonitor monitor) throws CoreException;
}