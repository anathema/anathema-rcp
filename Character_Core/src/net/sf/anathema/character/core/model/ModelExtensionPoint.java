package net.sf.anathema.character.core.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.internal.CharacterId;
import net.sf.anathema.character.core.model.initialize.IModelInitializer;
import net.sf.anathema.character.core.model.initialize.ModelInitializer;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.repository.internal.CharacterModelViewElement;
import net.sf.anathema.character.core.repository.internal.ModelDisplayConfiguration;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;
import org.eclipse.osgi.util.NLS;

public class ModelExtensionPoint {

  private static final String MODELS_EXTENSION_POINT = "models"; //$NON-NLS-1$
  private static final String ATTRIB_FILENAME = "filename"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_FACTORY = "modelFactory"; //$NON-NLS-1$

  public IModelInitializer createModel(IModelIdentifier identifier) {
    IExtensionElement extensionElement = getModelElement(identifier);
    if (extensionElement == null) {
      throw new IllegalArgumentException(NLS.bind(Messages.ModelCache_ModelNotFound_Message, identifier.getModelId()));
    }
    try {
      IModelFactory factory = extensionElement.getAttributeAsObject(ATTRIB_MODEL_FACTORY, IModelFactory.class);
      ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(identifier.getCharacterId());
      IContentHandle file = getFile(identifier, extensionElement);
      IModel model = factory.create(file, template);
      model.setClean();
      return new ModelInitializer(model, file, identifier);
    }
    catch (Exception e) {
      throw new IllegalArgumentException(Messages.ModelCache_ModelLoadError, e);
    }
  }

  public IContentHandle getModelContent(IModelIdentifier identifier) {
    return getFile(identifier, getModelElement(identifier));
  }

  private IContentHandle getFile(IModelIdentifier identifier, IExtensionElement modelElement) {
    String filename = modelElement.getAttribute(ATTRIB_FILENAME);
    return identifier.getCharacterId().getContents(filename);
  }

  private IExtensionElement getModelElement(IModelIdentifier identifier) {
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement extensionElement : extension.getElements()) {
        if (extensionElement.getAttribute(ATTRIB_ID).equals(identifier.getModelId())) {
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
    ICharacterTemplate template = templateProvider.getTemplate(new CharacterId(characterFolder));
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
    return new EclipseExtensionPoint(CharacterCorePlugin.ID, MODELS_EXTENSION_POINT).getExtensions();
  }
}