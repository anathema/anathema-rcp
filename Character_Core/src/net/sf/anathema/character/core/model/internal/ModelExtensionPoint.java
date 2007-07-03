package net.sf.anathema.character.core.model.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.repository.internal.CharacterModelViewElement;
import net.sf.anathema.character.core.repository.internal.ModelDisplayConfiguration;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;
import net.sf.anathema.character.core.template.ICharacterTemplate;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.osgi.util.NLS;

public class ModelExtensionPoint {

  private static final String EXTENSION_POINT_ID = "net.sf.anathema.character.models"; //$NON-NLS-1$
  private static final String ATTRIB_FILENAME = "filename"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_FACTORY = "modelFactory"; //$NON-NLS-1$

  public Object createModel(ModelIdentifier identifier) {
    IExtensionElement extensionElement = getModelElement(identifier);
    if (extensionElement == null) {
      throw new IllegalArgumentException(NLS.bind(Messages.ModelCache_ModelNotFound_Message, identifier.getId()));
    }
    try {
      IModelFactory factory = extensionElement.getAttributeAsObject(ATTRIB_MODEL_FACTORY, IModelFactory.class);
      IModel model = factory.create(getFile(identifier, extensionElement));
      model.setClean();
      return model;
    }
    catch (Exception e) {
      throw new IllegalArgumentException(Messages.ModelCache_ModelLoadError, e);
    }
  }

  public IFile getModelFile(ModelIdentifier identifier) {
    return getFile(identifier, getModelElement(identifier));
  }

  private IFile getFile(ModelIdentifier identifier, IExtensionElement modelElement) {
    String filename = modelElement.getAttribute(ATTRIB_FILENAME);
    return identifier.getFolder().getFile(filename);
  }

  private IExtensionElement getModelElement(ModelIdentifier identifier) {
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement extensionElement : extension.getElements()) {
        if (extensionElement.getAttribute(ATTRIB_ID).equals(identifier.getId())) {
          return extensionElement;
        }
      }
    }
    return null;
  }

  public IViewElement[] createViewElements(
      IViewElement parent,
      IFolder characterFolder,
      ICharacterTemplateProvider templateProvider) {
    ICharacterTemplate template = templateProvider.getTemplate(characterFolder);
    List<IViewElement> viewElements = new ArrayList<IViewElement>();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement modelElement : extension.getElements()) {
        String modelId = modelElement.getAttribute(ATTRIB_ID);
        IExtensionElement configurationElement = modelElement.getElement("displayConfiguration"); //$NON-NLS-1$
        if (configurationElement != null && template.supportsModel(modelId)) {
          String filename = modelElement.getAttribute(ATTRIB_FILENAME);
          String contributorId = extension.getContributorId();
          ModelDisplayConfiguration configuration = new ModelDisplayConfiguration(
              contributorId,
              filename,
              configurationElement);
          CharacterModelViewElement viewelElement = new CharacterModelViewElement(
              parent,
              characterFolder,
              configuration);
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