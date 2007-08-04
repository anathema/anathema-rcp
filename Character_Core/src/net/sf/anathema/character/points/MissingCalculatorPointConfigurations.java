package net.sf.anathema.character.points;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.internal.Messages;

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