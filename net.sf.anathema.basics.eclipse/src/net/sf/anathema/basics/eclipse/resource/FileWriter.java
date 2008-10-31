package net.sf.anathema.basics.eclipse.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class FileWriter {

  public void saveToFile(IFile file, ByteArrayOutputStream outputStream, IProgressMonitor monitor) throws CoreException {
    save(new FileContentHandle(file), outputStream, monitor);
  }


  public void save(IContentHandle content, ByteArrayOutputStream outputStream, IProgressMonitor monitor) throws CoreException {
    byte[] documentContent = outputStream.toByteArray();
    InputStream source = new ByteArrayInputStream(documentContent);
    content.setContents(source, monitor);
  }
}