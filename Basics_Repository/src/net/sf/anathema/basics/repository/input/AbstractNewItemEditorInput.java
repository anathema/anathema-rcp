package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public abstract class AbstractNewItemEditorInput implements IFileItemEditorInput {

  private IItem<IBasicItemData> item;
  private final IUnusedFileFactory unusedFileFactory;
  private IFile savefile;
  private final String untitledName;
  private final ImageDescriptor imageDescriptor;

  public AbstractNewItemEditorInput(
      IUnusedFileFactory unusedFileFactory,
      ImageDescriptor imageDescriptor,
      String untitledName) {
    this.unusedFileFactory = unusedFileFactory;
    this.imageDescriptor = imageDescriptor;
    this.untitledName = untitledName;
  }

  @Override
  public final IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    item = persister.createNew();
    return item;
  }

  @Override
  public final IItem<IBasicItemData> save(BasicDataItemPersister persister)
      throws IOException,
      CoreException,
      PersistenceException {
    if (this.savefile == null) {
      this.savefile = unusedFileFactory.createUnusedFile(getFileNameSuggestion(item));
    }
    saveToFile(persister);
    return item;
  }

  protected void saveToFile(BasicDataItemPersister persister) throws IOException, CoreException, PersistenceException {
    new ItemFileWriter().saveToFile(savefile, persister, item);
  }

  protected abstract String getFileNameSuggestion(IItem<IBasicItemData> saveItem);

  @Override
  public final boolean exists() {
    return false;
  }

  @Override
  public final ImageDescriptor getImageDescriptor() {
    return imageDescriptor;
  }

  @Override
  public final IPersistableElement getPersistable() {
    return null;
  }

  @Override
  public final String getToolTipText() {
    return getName();
  }

  @SuppressWarnings("unchecked")
  @Override
  public final Object getAdapter(Class adapter) {
    return null;
  }

  @Override
  public final IFile getFile() {
    return savefile;
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