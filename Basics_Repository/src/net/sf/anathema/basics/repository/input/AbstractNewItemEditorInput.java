package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledTextPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public abstract class AbstractNewItemEditorInput implements IFileItemEditorInput<ITitledText> {

  private ITitledText item;
  private final IUnusedFileFactory unusedFileFactory;
  private IFile savefile;
  private final ItemNameProvider provider;
  private final ImageDescriptor imageDescriptor;
  private final TitledTextPersister persister = new TitledTextPersister();

  public AbstractNewItemEditorInput(
      IUnusedFileFactory unusedFileFactory,
      ImageDescriptor imageDescriptor,
      String untitledName) {
    this.unusedFileFactory = unusedFileFactory;
    this.imageDescriptor = imageDescriptor;
    this.provider = new ItemNameProvider(untitledName);
    this.item = persister.createNew();
  }

  @Override
  public final ITitledText getItem() {
    return item;
  }

  @Override
  public final ITitledText save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    if (this.savefile == null) {
      this.savefile = unusedFileFactory.createUnusedFile(getFileNameSuggestion(item));
    }
    saveToFile(monitor);
    return item;
  }

  protected void saveToFile(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    new ItemFileWriter().saveToFile(savefile, persister, item, monitor);
  }

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
    return provider.getName(item);
  }

  protected String getFileNameSuggestion(ITitledText itemData) {
    String name = itemData.getName().getText();
    return StringUtilities.isNullOrTrimEmpty(name) ? "Unnamed" //$NON-NLS-1$
        : AnathemaStringUtilities.getFileNameRepresentation(name);
  }
}