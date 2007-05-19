package net.sf.anathema.framework.editor;

import java.io.IOException;

import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IBasicItemData;
import net.sf.anathema.framework.persistence.BasicDataItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class FileItemEditorInput extends FileEditorInput implements IItemEditorInput {

  private IItem<IBasicItemData> item;

  public FileItemEditorInput(IFile file) {
    super(file);
  }

  @Override
  public void save(BasicDataItemPersister persister) throws IOException, CoreException {
    new ItemFileWriter().saveToFile(getFile(), persister, item);
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