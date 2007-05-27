package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.disy.commons.core.util.StringUtilities;
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
  private final String untitledName;
  private final ImageDescriptor imageDescriptor;

  public FileItemEditorInput(IFile file, String untitledName, ImageDescriptor imageDescriptor) {
    super(file);
    this.untitledName = untitledName;
    this.imageDescriptor = imageDescriptor;
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
    if (item == null) {
      return "No item"; //$NON-NLS-1$
    }
    String name = item.getItemData().getDescription().getName().getText();
    if (StringUtilities.isNullOrEmpty(name)) {
      name = untitledName;
    }
    return name;
  }
}