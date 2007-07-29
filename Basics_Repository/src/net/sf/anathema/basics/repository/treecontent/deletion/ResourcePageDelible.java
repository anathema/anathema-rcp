package net.sf.anathema.basics.repository.treecontent.deletion;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ResourcePageDelible extends AbstractPageDelible {

  private final IResource resource;

  public ResourcePageDelible(ICloseHandler handler, IResource resource) {
    super(handler);
    this.resource = resource;
  }

  @Override
  protected void delete() throws CoreException, IOException {
    // TODO Monitor
    resource.delete(true, new NullProgressMonitor());
  }
}
