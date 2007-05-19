package net.sf.anathema.basics.repository.input;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IItemData;
import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ItemFileWriter {

  public <D extends IItemData> void saveToFile(IFile file, ISingleFileItemPersister<D> persister, IItem<D> item)
      throws IOException,
      CoreException,
      PersistenceException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      persister.save(outputStream, item);
      byte[] documentContent = outputStream.toByteArray();
      ByteArrayInputStream source = new ByteArrayInputStream(documentContent);
      if (file.exists()) {
        file.setContents(source, true, true, new NullProgressMonitor());
      }
      else {
        file.create(source, true, new NullProgressMonitor());
      }
      item.setClean();
    }
    finally {
      IOUtilities.close(outputStream);
    }
  }
}