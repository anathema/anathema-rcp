package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class FileItemEditorInput extends FileEditorInput implements IFileItemEditorInput {

  private IItem<IBasicItemData> item;

  public FileItemEditorInput(IFile file) {
    super(file);
  }

  @Override
  public void save(BasicDataItemPersister persister) throws IOException, CoreException, PersistenceException {
    new ItemFileWriter().saveToFile(getFile(), persister, item);
  }

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    item = persister.load(DocumentUtilities.read(getFile().getContents()));
    return item;
  }

  @Override
  public String getToolTipText() {
    return item.getPrintName();
  }
}