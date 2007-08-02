package net.sf.anathema.character.core.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.CharacterCorePlugin;
import net.sf.anathema.character.core.model.internal.CharacterId;
import net.sf.anathema.character.core.model.internal.Messages;
import net.sf.anathema.character.core.model.internal.MissingCalculatorPointConfigurations;
import net.sf.anathema.character.core.repository.internal.CharacterModelViewElement;
import net.sf.anathema.character.core.repository.internal.ModelDisplayConfiguration;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class ModelExtensionPoint implements IPointConfigurationProvider {

  private static final String EXTENSION_POINT_ID = "net.sf.anathema.character.models"; //$NON-NLS-1$
  private static final String ATTRIB_FILENAME = "filename"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_FACTORY = "modelFactory"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String ATTRIB_EXPERIENCE_POINT_CALCULATOR = "experiencePointCalculator"; //$NON-NLS-1$
  private static final String ATTRIB_BONUS_POINT_CALCULATOR = "bonusPointCalculator"; //$NON-NLS-1$

  public Object createModel(IModelIdentifier identifier) {
    IExtensionElement extensionElement = getModelElement(identifier);
    if (extensionElement == null) {
      throw new IllegalArgumentException(NLS.bind(Messages.ModelCache_ModelNotFound_Message, identifier.getModelId()));
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
    return new EclipseExtensionProvider().getExtensions(EXTENSION_POINT_ID);
  }

  public IPointConfiguration[] getExperiencePointConfigurations(
      ICharacterTemplateProvider provider,
      ICharacterId characterId) {
    return getPointConfigurations(provider, characterId, ATTRIB_EXPERIENCE_POINT_CALCULATOR);
  }

  public IPointConfiguration[] getBonusPointConfigurations(ICharacterTemplateProvider provider, ICharacterId characterId) {
    return getPointConfigurations(provider, characterId, ATTRIB_BONUS_POINT_CALCULATOR);
  }

  private IPointConfiguration[] getPointConfigurations(
      ICharacterTemplateProvider provider,
      ICharacterId characterId,
      String pointCalculatorAttribute) {
    ICharacterTemplate template = provider.getTemplate(characterId);
    List<IPointConfiguration> configurations = new ArrayList<IPointConfiguration>();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement modelElement : extension.getElements()) {
        String modelId = modelElement.getAttribute(ATTRIB_ID);
        IExtensionElement configurationElement = modelElement.getElement("pointConfiguration"); //$NON-NLS-1$
        if (configurationElement != null && template.supportsModel(modelId)) {
          String name = configurationElement.getAttribute(ATTRIB_NAME);
          IPointHandler handler = null;
          try {
            handler = configurationElement.getAttributeAsObject(pointCalculatorAttribute, IPointHandler.class);
          }
          catch (ExtensionException e) {
            CharacterCorePlugin.getDefaultInstance().log(
                IStatus.ERROR,
                Messages.ModelExtensionPoint_CalculatorLoadError,
                e);
          }
          if (handler == null) {
            configurations.add(new MissingCalculatorPointConfigurations(name));
          }
          else {
            configurations.add(new PointConfiguration(name, handler));
          }
        }
      }
    }
    return configurations.toArray(new IPointConfiguration[configurations.size()]);
  }
}