package net.sf.anathema.character.core.repository.internal;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.internal.CharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

public class ModelDisplayConfiguration implements IModelDisplayConfiguration {

  private final String filename;
  private final IExtensionElement displayElement;
  private final String pluginId;

  public ModelDisplayConfiguration(String pluginId, String filename, IExtensionElement displayElement) {
    this.pluginId = pluginId;
    this.filename = filename;
    this.displayElement = displayElement;
  }

  @Override
  public String getDisplayName() {
    return displayElement.getAttribute("displayName"); //$NON-NLS-1$
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return ImageDescriptor.createFromURL(ResourceUtils.getResourceUrl(pluginId, displayElement.getAttribute("icon"))); //$NON-NLS-1$
  }

  @Override
  public String getEditorId() {
    return displayElement.getAttribute("editorId"); //$NON-NLS-1$
  }

  @Override
  public IFile getModelFile(IContainer characterFolder) {
    return characterFolder.getFile(new Path(filename));
  }

  @Override
  public IEditorInput createEditorInput(
      IContainer characterFolder,
      ImageDescriptor descriptor,
      IDisplayNameProvider provider) throws PersistenceException, CoreException, ExtensionException {
    IEditorInputFactory factory = displayElement.getAttributeAsObject("editorInputFactory", //$NON-NLS-1$
        IEditorInputFactory.class);
    IFile modelFile = getModelFile(characterFolder);
    CharacterId characterId = new CharacterId(characterFolder);
    return factory.create(modelFile, characterId, descriptor, provider, ModelCache.getInstance());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ModelDisplayConfiguration)) {
      return false;
    }
    ModelDisplayConfiguration other = (ModelDisplayConfiguration) obj;
    return filename.equals(other.filename);
  }
}