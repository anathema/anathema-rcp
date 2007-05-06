package net.sf.anathema.framework.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IBasicItemData;
import net.sf.anathema.framework.persistence.BasicDataItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class FileItemEditorInput extends FileEditorInput implements IItemEditorInput {

  private IItem<IBasicItemData> item;

  public FileItemEditorInput(IFile file) {
    super(file);
  }

  @Override
  public void save(BasicDataItemPersister persister) throws IOException, CoreException {
    IFile file = getFile();
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

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    Document xmlDocument = DocumentUtilities.read(getFile().getContents());
    item = persister.load(xmlDocument);
    return item;
  }
  
  @Override
  public String getToolTipText() {
    return item.getDisplayName();
  }
}