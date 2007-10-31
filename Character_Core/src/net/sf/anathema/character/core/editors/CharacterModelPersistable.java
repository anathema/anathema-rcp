package net.sf.anathema.character.core.editors;

import java.net.URL;


import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class CharacterModelPersistable implements IPersistableElement {

  public static final String PROP_IMAGE_DESCRIPTOR_URL = "imageDescriptorUrl"; //$NON-NLS-1$
  private final IPath filePath;
  private final URL imageUrl;
  private final String factoryId;

  public CharacterModelPersistable(IPath filePath, URL imageUrl, String factoryId) {
    this.filePath = filePath;
    this.imageUrl = imageUrl;
    this.factoryId = factoryId;
  }

  @Override
  public String getFactoryId() {
    return factoryId;
  }

  @Override
  public void saveState(IMemento memento) {
    memento.putString(AbstractModelPersistableFactory.PROP_FULL_PATH, filePath.toPortableString());
    memento.putString(PROP_IMAGE_DESCRIPTOR_URL, imageUrl.toExternalForm());
  }
}