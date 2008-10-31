package net.sf.anathema.basics.repository.treecontent.deletion;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ResourcePageDelible extends AbstractPageDelible {

  private final IResource resource;

  public ResourcePageDelible(ICloseHandler handler, IResource resource) {
    super(handler);
    this.resource = resource;
  }

  @Override
  protected void delete(IProgressMonitor monitor) throws CoreException, IOException {
    resource.delete(true, monitor);
  }
}