package net.sf.anathema.character.points.configuration.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.points.configuration.NullPointHandler;
import net.sf.anathema.character.points.plugin.PointPluginConstants;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class PointConfigurationExtensionPoint implements IPointConfigurationProvider {

  private static final String EXTENSION_POINT_ID = "net.sf.anathema.character.points.configuration"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String ATTRIB_EXPERIENCE_POINT_CALCULATOR = "experiencePointCalculator"; //$NON-NLS-1$
  private static final String ATTRIB_BONUS_POINT_CALCULATOR = "bonusPointCalculator"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final Logger logger = new Logger(PointPluginConstants.PLUGIN_ID);

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
    MultiEntryMap<String, IPointHandler> handlersByGroupId = new MultiEntryMap<String, IPointHandler>();
    Map<String, PointConfiguration> configurationsByGroupId = new HashMap<String, PointConfiguration>();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement configurationElement : extension.getElements()) {
        if ("pointConfiguration".equals(configurationElement.getName())) { //$NON-NLS-1$
          String groupReference = configurationElement.getAttribute("groupReference"); //$NON-NLS-1$
          IPointHandler handler = getPointHandler(pointCalculatorAttribute, configurationElement);
          handlersByGroupId.add(groupReference, handler);
        }
        else {
          String groupId =  configurationElement.getAttribute("groupId"); //$NON-NLS-1$
          String name = configurationElement.getAttribute(ATTRIB_NAME);
          String modelId = configurationElement.getAttribute(ATTRIB_MODEL_ID);
          if (template.supportsModel(modelId)) {
            configurationsByGroupId.put(groupId, new PointConfiguration(name));
          }
        }
      }
    }
    List<IPointConfiguration> configurations = new ArrayList<IPointConfiguration>();
    for (String groupId : configurationsByGroupId.keySet()) {
      List<IPointHandler> handlers = handlersByGroupId.get(groupId);
      if (handlers.isEmpty()) {
        continue;
      }
      PointConfiguration configuration = configurationsByGroupId.get(groupId);
      configuration.addHandlers(handlers);
      configurations.add(configuration);
    }
    return configurations.toArray(new IPointConfiguration[configurations.size()]);
  }

  // todo template support
  // 

  private IPointHandler getPointHandler(String pointCalculatorAttribute, IExtensionElement configurationElement) {
    try {
      return configurationElement.getAttributeAsObject(pointCalculatorAttribute, IPointHandler.class);
    }
    catch (ExtensionException e) {
      logger.error(Messages.PointConfigurationExtensionPoint_CalculatorLoadError, e);
      return new NullPointHandler();
    }
  }

  private IPluginExtension[] getPluginExtensions() {
    return new EclipseExtensionProvider().getExtensions(EXTENSION_POINT_ID);
  }
}
