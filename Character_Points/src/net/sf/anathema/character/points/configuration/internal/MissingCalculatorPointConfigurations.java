package net.sf.anathema.character.points.configuration.internal;

import net.sf.anathema.character.core.model.ICharacterId;

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
  public String getPoints(ICharacterId characterId) {
    return Messages.MissingCalculatorPointConfigurations_PointsNA;
  }
}