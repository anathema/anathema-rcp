package net.sf.anathema.basics.repository.input.internal;

import java.net.URL;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class FileItemEditorInputPersistableElement implements IPersistableElement {

  private final String untitledName;
  private final IPath filePath;
  private final URL imageUrl;

  public FileItemEditorInputPersistableElement(String untitledName, IPath filePath, URL imageUrl) {
    this.untitledName = untitledName;
    this.filePath = filePath;
    this.imageUrl = imageUrl;
  }
  
  @Override
  public String getFactoryId() {
    return "FileItemEditorInputFactory"; //$NON-NLS-1$
  }

  @Override
  public void saveState(IMemento memento) {
    memento.putString(FileItemEditorInputFactory.PROP_FULL_PATH, filePath.toPortableString());
    memento.putString(FileItemEditorInputFactory.PROP_UNTITLED_NAME, untitledName);
    memento.putString(FileItemEditorInputFactory.PROP_IMAGE_DESCRIPTOR_URL, imageUrl.toExternalForm());
  }
}