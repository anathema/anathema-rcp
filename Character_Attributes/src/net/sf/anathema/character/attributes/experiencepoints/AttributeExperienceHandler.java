package net.sf.anathema.character.attributes.experiencepoints;

import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experiencepoints.IExperiencePointHandler;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class AttributeExperienceHandler implements IExperiencePointHandler {

  @Override
  public int getPoints(IFolder folder) {
    ModelIdentifier identifier = new ModelIdentifier(folder, IAttributes.MODEL_ID);
    IAttributes attributes = (IAttributes) ModelCache.getInstance().getModel(identifier);
    return new AttributeExperienceCalculator(attributes).calculate();
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}