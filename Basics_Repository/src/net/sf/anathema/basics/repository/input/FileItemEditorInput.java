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
import org.eclipse.jface.resource.ImageDescriptor;

public class FileItemEditorInput extends FileEditorInput implements IFileItemEditorInput {

  private IItem<IBasicItemData> item;
  private final ImageDescriptor imageDescriptor;
  private final ItemNameProvider provider;

  public FileItemEditorInput(IFile file, String untitledName, ImageDescriptor imageDescriptor) {
    super(file);
    this.imageDescriptor = imageDescriptor;
    this.provider = new ItemNameProvider(untitledName);
  }

  @Override
  public IItem<IBasicItemData> save(BasicDataItemPersister persister)
      throws IOException,
      CoreException,
      PersistenceException {
    new ItemFileWriter().saveToFile(getFile(), persister, item);
    return item;
  }

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    item = persister.load(DocumentUtilities.read(getFile().getContents()));
    return item;
  }

  public void setItem(IItem<IBasicItemData> item) {
    this.item = item;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return imageDescriptor;
  }

  @Override
  public String getToolTipText() {
    return getName();
  }

  @Override
  public String getName() {
    return provider.getName(item);
  }
}