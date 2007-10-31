package net.sf.anathema.character.description.editor;

import java.net.URL;

import net.sf.anathema.character.core.editors.AbstractModelPersistableFactory;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class CharacterDescriptionPersistable implements IPersistableElement {

  public static final String PROP_IMAGE_DESCRIPTOR_URL = "imageDescriptorUrl"; //$NON-NLS-1$
  private final IPath filePath;
  private final URL imageUrl;

  public CharacterDescriptionPersistable(IPath filePath, URL imageUrl) {
    this.filePath = filePath;
    this.imageUrl = imageUrl;
  }
  
  @Override
  public String getFactoryId() {
    return "CharacterDescriptionEditorInputFactory"; //$NON-NLS-1$
  }

  @Override
  public void saveState(IMemento memento) {
    memento.putString(AbstractModelPersistableFactory.PROP_FULL_PATH, filePath.toPortableString());
    memento.putString(PROP_IMAGE_DESCRIPTOR_URL, imageUrl.toExternalForm());
  }
}