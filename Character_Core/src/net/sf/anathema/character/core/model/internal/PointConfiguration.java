package net.sf.anathema.character.core.model.internal;

import org.eclipse.core.resources.IFolder;

import net.sf.anathema.character.experiencepoints.IExperiencePointHandler;

public class PointConfiguration implements IPointConfiguration {

  private final String name;
  private final IExperiencePointHandler calculator;

  public PointConfiguration(String name, IExperiencePointHandler calculator) {
    this.name = name;
    this.calculator = calculator;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getExperiencePoints(IFolder folder) {
    return String.valueOf(calculator.getPoints(folder));
  }
}