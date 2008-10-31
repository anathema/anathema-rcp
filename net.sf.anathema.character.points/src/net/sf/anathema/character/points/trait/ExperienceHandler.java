package net.sf.anathema.character.points.trait;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class ExperienceHandler implements IPointHandler {

  private static final String TAG_EXPERIENCE_CONFIGURATIONS = "experienceConfigurations"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private String modelId;
  private int baseCost;
  private Integer newCost;

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, modelId);
    ITraitCollectionModel attributes = (ITraitCollectionModel) ModelCache.getInstance().getModel(identifier);
    return new TraitCollectionExperienceCalculator(attributes, baseCost, newCost).calculate();
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    String configurationId = config.getAttribute("configurationId"); //$NON-NLS-1$
    IConfigurationElement configurationsElement = getConfigurationsElement(config);
    IConfigurationElement traitConfigurationElement = getConfigurationElement(configurationsElement, configurationId);
    modelId = traitConfigurationElement.getAttribute("modelId"); //$NON-NLS-1$
    baseCost = Integer.valueOf(traitConfigurationElement.getAttribute("baseCost")); //$NON-NLS-1$
    String newCostString = traitConfigurationElement.getAttribute("newCost"); //$NON-NLS-1$
    if (newCostString != null) {
      newCost = Integer.valueOf(newCostString);
    }
  }

  private IConfigurationElement getConfigurationElement(
      IConfigurationElement configurationsElement,
      String configurationId) {
    for (IConfigurationElement child : configurationsElement.getChildren()) {
      if (child.getAttribute(ATTRIB_ID).equals(configurationId)) {
        return child;
      }
    }
    return null;
  }

  private IConfigurationElement getConfigurationsElement(IConfigurationElement config) {
    for (IConfigurationElement element : config.getDeclaringExtension().getConfigurationElements()) {
      if (element.getName().equals(TAG_EXPERIENCE_CONFIGURATIONS)) {
        return element;
      }
    }
    return null;
  }
}