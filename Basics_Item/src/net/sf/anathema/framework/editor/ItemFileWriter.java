package net.sf.anathema.framework.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.framework.persistence.ISingleFileItemPersister;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ItemFileWriter {

  public <D extends IItemData> void saveToFile(IFile file, ISingleFileItemPersister<D> persister, IItem<D> item)
      throws IOException,
      CoreException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      persister.save(outputStream, item);
      byte[] documentContent = outputStream.toByteArray();
      ByteArrayInputStream source = new ByteArrayInputStream(documentContent);
      file.setContents(source, true, true, new NullProgressMonitor());
      item.setClean();
    }
    finally {
      IOUtilities.close(outputStream);
    }
  }
}