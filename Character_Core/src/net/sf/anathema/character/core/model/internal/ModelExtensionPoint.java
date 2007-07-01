package net.sf.anathema.character.core.model.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.CharacterCorePlugin;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.repository.internal.CharacterModelViewElement;
import net.sf.anathema.character.core.repository.internal.ModelDisplayConfiguration;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class ModelExtensionPoint {

  private static final String EXTENSION_POINT_ID = "net.sf.anathema.character.models"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_FACTORY = "modelFactory"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$

  public Object createModel(ModelIdentifier identifier){
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement extensionElement : extension.getElements()) {
        if (extensionElement.getAttribute(ATTRIB_ID).equals(identifier.getId())) {
          try {
            IModelFactory factory = extensionElement.getAttributeAsObject(ATTRIB_MODEL_FACTORY, IModelFactory.class);
            IFolder characterFolder = identifier.getFolder();
            String filename = extensionElement.getAttribute("filename"); //$NON-NLS-1$
            return factory.create(characterFolder.getFile(filename));
          }
          catch (Exception e) {
            CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, Messages.ModelCache_ModelLoadError, e);
          }
        }
      }
    }
    throw new IllegalArgumentException(NLS.bind(Messages.ModelCache_ModelNotFound_Message, identifier.getId()));
  }

  public IViewElement[] createViewElements(IViewElement parent, IFolder characterFolder) {
    List<IViewElement> viewElements = new ArrayList<IViewElement>();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement extensionElement : extension.getElements()) {
        IExtensionElement configurationElement = extensionElement.getElement("displayConfiguration"); //$NON-NLS-1$
        if (configurationElement != null) {
          String filename = extensionElement.getAttribute("filename"); //$NON-NLS-1$
          String contributorId = extension.getContributorId();
          ModelDisplayConfiguration configuration = new ModelDisplayConfiguration(
              contributorId,
              filename,
              configurationElement);
          CharacterModelViewElement viewelElement = new CharacterModelViewElement(parent, characterFolder, configuration);
          viewElements.add(viewelElement);
        }
      }
    }
    return viewElements.toArray(new IViewElement[viewElements.size()]);
  }

  private IPluginExtension[] getPluginExtensions() {
    return new EclipseExtensionProvider().getExtensions(EXTENSION_POINT_ID);
  }
}