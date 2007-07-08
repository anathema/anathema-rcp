package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.experiencepoints.IExperiencePointCalculator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class AttributeExperienceCalculator implements IExperiencePointCalculator {

  @Override
  public int getPoints() {
    return 2;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}