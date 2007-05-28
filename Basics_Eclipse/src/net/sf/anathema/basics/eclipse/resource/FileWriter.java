package net.sf.anathema.basics.eclipse.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class FileWriter {

  public void saveToFile(IFile file, ByteArrayOutputStream outputStream, IProgressMonitor monitor) throws CoreException {
    byte[] documentContent = outputStream.toByteArray();
    ByteArrayInputStream source = new ByteArrayInputStream(documentContent);
    if (file.exists()) {
      file.setContents(source, true, true, monitor);
    }
    else {
      file.create(source, true, monitor);
    }
  }
}