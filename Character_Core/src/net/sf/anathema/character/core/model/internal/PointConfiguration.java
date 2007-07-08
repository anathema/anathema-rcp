package net.sf.anathema.character.core.model.internal;

import net.sf.anathema.character.experiencepoints.IExperiencePointCalculator;

public class PointConfiguration implements IPointConfiguration {

  private final String name;
  private final IExperiencePointCalculator calculator;

  public PointConfiguration(String name, IExperiencePointCalculator calculator) {
    this.name = name;
    this.calculator = calculator;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getExperiencePoints() {
    return String.valueOf(calculator.getPoints());
  }
}