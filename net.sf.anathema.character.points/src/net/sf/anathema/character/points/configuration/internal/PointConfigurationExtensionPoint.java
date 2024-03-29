package net.sf.anathema.character.points.configuration.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.ILogger;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.points.plugin.PointPluginConstants;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class PointConfigurationExtensionPoint implements IPointConfigurationProvider {

  private static final String ATTRIB_GROUP_ID = "groupId"; //$NON-NLS-1$
  private static final String ATTRIB_GROUP_REFERENCE = "groupReference"; //$NON-NLS-1$
  private static final String ATTRIB_HANDLER_CLASS = "handlerClass"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String TAG_BONUS_POINT_CALCULATOR = "bonusPointCalculator"; //$NON-NLS-1$
  private static final String TAG_EXPERIENCE_POINT_CALCULATOR = "experiencePointCalculator"; //$NON-NLS-1$
  private static final String TAG_POINT_GROUP = "pointGroup"; //$NON-NLS-1$
  private static final String CONFIGURATIONS_EXTENSION_POINT = "configurations"; //$NON-NLS-1$
  private static final ILogger logger = new Logger(PointPluginConstants.PLUGIN_ID);

  public List<IPointConfiguration> getExperiencePointConfigurations(ICharacterTemplate template) {
    return getPointConfigurations(template, TAG_EXPERIENCE_POINT_CALCULATOR);
  }

  public List<IPointConfiguration> getBonusPointConfigurations(ICharacterTemplate template) {
    return getPointConfigurations(template, TAG_BONUS_POINT_CALCULATOR);
  }

  private List<IPointConfiguration> getPointConfigurations(
      ICharacterTemplate template,
      String pointCalculatorElementName) {
    MultiEntryMap<String, IPointHandler> handlersByGroupId = new MultiEntryMap<String, IPointHandler>();
    Map<String, PointConfiguration> configurationsByGroupId = new HashMap<String, PointConfiguration>();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement configurationElement : extension.getElements()) {
        if (pointCalculatorElementName.equals(configurationElement.getName())) {
          String groupReference = configurationElement.getAttribute(ATTRIB_GROUP_REFERENCE);
          IPointHandler handler = getPointHandler(configurationElement);
          handlersByGroupId.add(groupReference, handler);
        }
        else if (TAG_POINT_GROUP.equals(configurationElement.getName())) {
          String groupId = configurationElement.getAttribute(ATTRIB_GROUP_ID);
          String name = configurationElement.getAttribute(ATTRIB_NAME);
          String modelId = configurationElement.getAttribute(ATTRIB_MODEL_ID);
          if (new ModelExtensionPoint().getModelDescriptor(modelId).isSupportedBy(template)) {
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
    return configurations;
  }

  private IPointHandler getPointHandler(IExtensionElement element) {
    try {
      return element.getAttributeAsObject(ATTRIB_HANDLER_CLASS, IPointHandler.class);
    }
    catch (ExtensionException e) {
      logger.error(Messages.PointConfigurationExtensionPoint_CalculatorLoadError, e);
      return new NullPointHandler();
    }
  }

  private IPluginExtension[] getPluginExtensions() {
    return new EclipseExtensionPoint(PointPluginConstants.PLUGIN_ID, CONFIGURATIONS_EXTENSION_POINT).getExtensions();
  }
}