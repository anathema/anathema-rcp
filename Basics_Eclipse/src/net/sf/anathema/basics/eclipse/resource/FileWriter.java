package net.sf.anathema.basics.eclipse.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class FileWriter {

  public void saveToFile(IFile file, ByteArrayOutputStream outputStream) throws CoreException {
    byte[] documentContent = outputStream.toByteArray();
    ByteArrayInputStream source = new ByteArrayInputStream(documentContent);
    if (file.exists()) {
      file.setContents(source, true, true, new NullProgressMonitor());
    }
    else {
      file.create(source, true, new NullProgressMonitor());
    }
  }
}