package net.sf.anathema.character.points.configuration.internal;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.points.configuration.IPointHandler;


public class PointConfiguration implements IPointConfiguration {

  private final String name;
  private final IPointHandler handler;

  public PointConfiguration(String name, IPointHandler handler) {
    this.name = name;
    this.handler = handler;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPoints(ICharacterId characterId) {
    return String.valueOf(handler.getPoints(characterId));
  }
}