package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledTextPersister;
import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class FileItemEditorInput extends FileEditorInput implements IFileItemEditorInput<ITitledText> {

  private ITitledText item;
  private final ImageDescriptor imageDescriptor;
  private final ItemNameProvider provider;
  private final TitledTextPersister persister = new TitledTextPersister();

  public FileItemEditorInput(IFile file, String untitledName, ImageDescriptor imageDescriptor)
      throws PersistenceException,
      CoreException {
    super(file);
    this.imageDescriptor = imageDescriptor;
    this.provider = new ItemNameProvider(untitledName);
    this.item = persister.load(DocumentUtilities.read(getFile().getContents()));
  }

  @Override
  public ITitledText save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    new ItemFileWriter().saveToFile(getFile(), persister, item, monitor);
    return item;
  }

  @Override
  public ITitledText getItem() {
    return item;
  }

  public void setItem(ITitledText item) {
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