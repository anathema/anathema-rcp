package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class NewItemEditorInput implements IFileItemEditorInput {

  private IItem<IBasicItemData> item;
  private final IUnusedFileFactory unusedFileFactory;
  private IFile savefile;
  private final String untitledName;

  protected NewItemEditorInput(IItemType itemType) {
    this(new UnusedFileFactory(itemType), itemType.getUntitledName());
  }
  
  protected NewItemEditorInput(IUnusedFileFactory unusedFileFactory, String untitledName) {
    this.unusedFileFactory = unusedFileFactory;
    this.untitledName = untitledName;
  }

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    item = persister.createNew();
    return item;
  }

  @Override
  public IItem<IBasicItemData> save(BasicDataItemPersister persister) throws IOException, CoreException, PersistenceException {
    if (this.savefile == null) {
      this.savefile = createUnusedFile();
    }
    new ItemFileWriter().saveToFile(savefile, persister, item);
    return item;
  }

  private IFile createUnusedFile() throws CoreException {
    String fileNameSuggestion = AnathemaStringUtilities.getFileNameRepresentation(item.getPrintName());
    return unusedFileFactory.createUnusedFile(fileNameSuggestion);
  }

  @Override
  public boolean exists() {
    return false;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return null;
  }

  @Override
  public String getName() {
    return "NewItemEditorInput"; //$NON-NLS-1$
  }

  @Override
  public IPersistableElement getPersistable() {
    return null;
  }

  @Override
  public String getToolTipText() {
    return item.getPrintName();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return null;
  }

  @Override
  public IFile getFile() {
    return savefile;
  }
  
  @Override
  public String getDisplayName() {
    String name = item.getItemData().getDescription().getName().getText();
    if (StringUtilities.isNullOrEmpty(name)) {
      name = untitledName;
    }
    return name;
  }
}