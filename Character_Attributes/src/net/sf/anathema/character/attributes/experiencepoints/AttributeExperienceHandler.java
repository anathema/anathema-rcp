package net.sf.anathema.character.attributes.experiencepoints;

import java.util.Random;

import net.sf.anathema.character.experiencepoints.IExperiencePointCalculator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class AttributeExperienceHandler implements IExperiencePointCalculator {

  private static Random random = new Random();

  @Override
  public int getPoints() {
    return random.nextInt();
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}