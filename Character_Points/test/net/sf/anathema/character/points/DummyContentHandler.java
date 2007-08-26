package net.sf.anathema.character.points;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class DummyContentHandler implements IContentHandle {

  private String content;

  public DummyContentHandler(String content) {
    this.content = content;
  }

  @Override
  public boolean exists() {
    return content != null;
  }

  @Override
  public InputStream getContents() throws CoreException {
    return new ByteArrayInputStream(content.getBytes());
  }

  @Override
  public void setContents(InputStream source, IProgressMonitor monitor) throws CoreException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      IOUtilities.copyStream(source, outputStream);
    }
    catch (IOException e) {
      throw new CoreException(new Status(
          IStatus.ERROR,
          "net.sf.anathema.character.core", //$NON-NLS-1$
          "Error reading from content.", //$NON-NLS-1$
          e));
    }
    content = new String(outputStream.toByteArray());
  }

  public void setContent(String content) {
    this.content = content;
  }
  
  public String getContent() {
    return content;
  }
}