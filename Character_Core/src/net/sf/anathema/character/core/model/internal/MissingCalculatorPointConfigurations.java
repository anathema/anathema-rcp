package net.sf.anathema.character.core.model.internal;

import org.eclipse.core.resources.IFolder;

public class MissingCalculatorPointConfigurations implements IPointConfiguration {

  private final String name;

  public MissingCalculatorPointConfigurations(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getExperiencePoints(IFolder folder) {
    return "N/A";
  }
}