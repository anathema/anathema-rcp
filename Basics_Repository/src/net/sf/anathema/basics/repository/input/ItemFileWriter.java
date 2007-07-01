package net.sf.anathema.basics.repository.input;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ItemFileWriter {

  public <D extends IItem> void saveToFile(
      IFile file,
      ISingleFileItemPersister<D> persister,
      D item,
      IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      persister.save(outputStream, item);
      new FileWriter().saveToFile(file, outputStream, monitor);
      item.setClean();
    }
    finally {
      IOUtilities.close(outputStream);
    }
  }
}