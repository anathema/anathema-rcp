package net.sf.anathema.basics.repository.input.internal;

import java.net.URL;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class PersistableElement implements IPersistableElement {

  private final String untitledName;
  private final IPath filePath;
  private final URL imageUrl;

  public PersistableElement(String untitledName, IPath filePath, URL imageUrl) {
    this.untitledName = untitledName;
    this.filePath = filePath;
    this.imageUrl = imageUrl;
  }
  
  @Override
  public String getFactoryId() {
    return "test.the.west";
  }

  @Override
  public void saveState(IMemento memento) {
    memento.putString(PersistableElementFactory.PROP_FULL_PATH, filePath.toPortableString());
    memento.putString(PersistableElementFactory.PROP_UNTITLED_NAME, untitledName);
    memento.putString(PersistableElementFactory.PROP_IMAGE_DESCRIPTOR_URL, imageUrl.toExternalForm());
  }
}