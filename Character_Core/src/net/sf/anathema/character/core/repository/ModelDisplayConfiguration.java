package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
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
  public IFile getModelFile(IFolder characterFolder) {
    return characterFolder.getFile(filename);
  }

  @Override
  public IEditorInput createEditorInput(
      IFolder characterFolder,
      ImageDescriptor descriptor,
      IDisplayNameProvider provider) throws PersistenceException, CoreException, ExtensionException {
    IEditorInputFactory factory = displayElement.getAttributeAsObject("editorInputFactory", //$NON-NLS-1$
        IEditorInputFactory.class);
    IFile modelFile = getModelFile(characterFolder);
    return factory.create(modelFile, characterFolder, descriptor, provider, ModelCache.getInstance());
  }
}