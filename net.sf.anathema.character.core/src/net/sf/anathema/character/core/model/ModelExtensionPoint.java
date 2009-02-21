package net.sf.anathema.character.core.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.display.ViewElementConfiguratorProvider;
import net.sf.anathema.character.core.model.internal.ModelDescriptor;
import net.sf.anathema.character.core.model.internal.NullModelDescriptor;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.repository.internal.CharacterModelViewElement;
import net.sf.anathema.character.core.repository.internal.ModelDisplayConfiguration;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.osgi.util.NLS;

public class ModelExtensionPoint {

  private static final String TAG_DISPLAY_CONFIGURATION = "displayConfiguration"; //$NON-NLS-1$
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
      IModelFactory< ? > factory = extensionElement.getAttributeAsObject(ATTRIB_MODEL_FACTORY, IModelFactory.class);
      ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(identifier.getCharacterId());
      ICharacterType characterType = new CharacterTypeFinder().getCharacterType(identifier.getCharacterId());
      IContentHandle file = getFile(identifier, extensionElement);
      return factory.createInitializer(file, template, characterType, identifier);
    }
    catch (Exception e) {
      throw new IllegalArgumentException(
          NLS.bind(Messages.ModelCache_ModelLoadError_Format, identifier.getModelId()),
          e);
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
    return getModelElement(identifier.getModelId());
  }

  public IExtensionElement getModelElement(String modelId) {
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement extensionElement : extension.getElements()) {
        if (extensionElement.getAttribute(ATTRIB_ID).equals(modelId)) {
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
    List<IViewElement> viewElements = new ArrayList<IViewElement>();
    for (IExtensionElement modelElement : getDisplayModelElements(new CharacterId(characterFolder), templateProvider)) {
      IExtensionElement configurationElement = modelElement.getElement(TAG_DISPLAY_CONFIGURATION);
      IModelDisplayConfiguration configuration = createModelDisplayConfiguration(modelElement, configurationElement);
      CharacterModelViewElement modelViewElement = new CharacterModelViewElement(parent, characterFolder, configuration);
      viewElements.add(modelViewElement);
      String modelId = modelElement.getAttribute(ATTRIB_ID);
      for (IViewElementConfigurator configurator : new ViewElementConfiguratorProvider().getFor(modelId)) {
        configurator.configure(modelViewElement);
      }
    }
    return viewElements.toArray(new IViewElement[viewElements.size()]);
  }

  private IModelDisplayConfiguration createModelDisplayConfiguration(
      IExtensionElement modelElement,
      IExtensionElement configurationElement) {
    String filename = modelElement.getAttribute(ATTRIB_FILENAME);
    String contributorId = modelElement.getContributorId();
    return new ModelDisplayConfiguration(contributorId, filename, configurationElement);
  }

  public Iterable<String> getModelFileNames(ICharacterId characterId, ICharacterTemplateProvider templateProvider) {
    List<String> modelFileNames = new ArrayList<String>();
    for (IExtensionElement modelElement : getDisplayModelElements(characterId, templateProvider)) {
      modelFileNames.add(modelElement.getAttribute(ATTRIB_FILENAME));
    }
    return modelFileNames;
  }

  private Iterable<IExtensionElement> getDisplayModelElements(
      ICharacterId characterId,
      ICharacterTemplateProvider templateProvider) {
    final ICharacterTemplate template = templateProvider.getTemplate(characterId);
    return getDisplayModelElements(template);
  }

  private Iterable<IExtensionElement> getDisplayModelElements(ICharacterTemplate template) {
    List<IExtensionElement> supportedElements = new ArrayList<IExtensionElement>();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement modelElement : extension.getElements()) {
        IModelDescriptor modelDescriptor = ModelDescriptor.create(modelElement);
        IExtensionElement configurationElement = modelElement.getElement(TAG_DISPLAY_CONFIGURATION);
        if (configurationElement != IExtensionElement.NO_ELEMENT && modelDescriptor.isSupportedBy(template)) {
          supportedElements.add(modelElement);
        }
      }
    }
    return supportedElements;
  }

  public IModelDisplayConfiguration getDisplayConfiguration(String modelId) {
    IExtensionElement modelElement = getModelElement(modelId);
    if (modelElement == null) {
      return null;
    }
    IExtensionElement configurationElement = modelElement.getElement(TAG_DISPLAY_CONFIGURATION);
    if (configurationElement != IExtensionElement.NO_ELEMENT) {
      return createModelDisplayConfiguration(modelElement, configurationElement);
    }
    return null;
  }

  public String getModelFilename(String modelId) {
    IExtensionElement modelElement = getModelElement(modelId);
    if (modelElement == null) {
      return null;
    }
    return modelElement.getAttribute(ATTRIB_FILENAME);
  }

  public IModelDisplayConfiguration getDisplayConfiguration(IResource resource) {
    String fileName = resource.getName();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement modelElement : extension.getElements()) {
        IExtensionElement configurationElement = modelElement.getElement(TAG_DISPLAY_CONFIGURATION);
        if (configurationElement != IExtensionElement.NO_ELEMENT) {
          String filenameAttribute = modelElement.getAttribute(ATTRIB_FILENAME);
          if (fileName.equals(filenameAttribute)) {
            return createModelDisplayConfiguration(modelElement, configurationElement);
          }
        }
      }
    }
    return null;
  }

  public IModelDescriptor getModelDescriptor(String modelId) {
    IExtensionElement modelElement = getModelElement(modelId);
    if (modelElement == null) {
      return new NullModelDescriptor();
    }
    return ModelDescriptor.create(modelElement);
  }

  private IPluginExtension[] getPluginExtensions() {
    return new EclipseExtensionPoint(CharacterCorePlugin.ID, MODELS_EXTENSION_POINT).getExtensions();
  }
}