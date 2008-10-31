package net.sf.anathema.character.core.editors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class ModelPersistable implements IPersistableElement {

  public static final String PROP_IMAGE_DESCRIPTOR_URL = "imageDescriptorUrl"; //$NON-NLS-1$
  private final IPath filePath;

  public ModelPersistable(IPath filePath) {
    this.filePath = filePath;
  }

  @Override
  public String getFactoryId() {
    return "CharacterModelPersistableFactory"; //$NON-NLS-1$
  }

  @Override
  public void saveState(IMemento memento) {
    memento.putString(ModelPersistableFactory.PROP_FULL_PATH, filePath.toPortableString());
  }
}