package net.sf.anathema.basics.repository.input.internal;

import java.io.IOException;
import java.net.URL;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledTextPersister;
import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class FileItemEditorInput extends FileEditorInput implements IFileItemEditorInput<ITitledText> {

  private ITitledText item;
  private final ItemNameProvider provider;
  private final TitledTextPersister persister = new TitledTextPersister();
  private FileItemEditorInputPersistableElement persistable;
  private final URL imageUrl;

  public FileItemEditorInput(IFile file, String untitledName, URL imageUrl)
      throws PersistenceException,
      CoreException {
    super(file, ImageDescriptor.createFromURL(imageUrl));
    this.imageUrl = imageUrl;
    this.persistable = new FileItemEditorInputPersistableElement(untitledName, file.getFullPath(), imageUrl); 
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

  @Override
  public IPersistableElement getPersistable() {
    return persistable;
  }

  public void setItem(ITitledText item) {
    this.item = item;
  }
  
  @Override
  public URL getImageDescriptorUrl() {
    return imageUrl;
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