package net.sf.anathema.character.points;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;
import net.sf.anathema.character.points.plugin.PointPluginConstants;

import org.eclipse.core.runtime.IStatus;

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
    List<IPointConfiguration> configurations = new ArrayList<IPointConfiguration>();
    for (IPluginExtension extension : getPluginExtensions()) {
      for (IExtensionElement configurationElement : extension.getElements()) {
        String modelId = configurationElement.getAttribute(ATTRIB_MODEL_ID);
        if (configurationElement != null && template.supportsModel(modelId)) {
          String name = configurationElement.getAttribute(ATTRIB_NAME);
          IPointHandler handler = null;
          try {
            handler = configurationElement.getAttributeAsObject(pointCalculatorAttribute, IPointHandler.class);
          }
          catch (ExtensionException e) {
            logger.log(
                IStatus.ERROR,
                Messages.PointConfigurationExtensionPoint_CalculatorLoadError,
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

  private IPluginExtension[] getPluginExtensions() {
    return new EclipseExtensionProvider().getExtensions(EXTENSION_POINT_ID);
  }
}
