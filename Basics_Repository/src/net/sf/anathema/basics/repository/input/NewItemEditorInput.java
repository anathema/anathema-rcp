package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IItemEditorInput;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class NewItemEditorInput implements IItemEditorInput {

  private IItem<IBasicItemData> item;
  private final IItemType itemType;
  private IFile savefile;

  public NewItemEditorInput(IItemType itemType) {
    this.itemType = itemType;
  }

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    item = persister.createNew();
    return item;
  }

  @Override
  public void save(BasicDataItemPersister persister) throws IOException, CoreException, PersistenceException {
    if (this.savefile == null) {
      this.savefile = createUnusedFile();
    }
    new ItemFileWriter().saveToFile(savefile, persister, item);
  }

  private IFile createUnusedFile() {
    String fileNameSuggestion = AnathemaStringUtilities.getFileNameRepresentation(item.getPrintName());
    return new UnusedFileFactory().createUnusedFile(fileNameSuggestion, itemType);
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
}