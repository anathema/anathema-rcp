package net.sf.anathema.character.points.configuration.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.IPointHandler;


public class PointConfiguration implements IPointConfiguration {

  private final String name;
  private final List<IPointHandler> allHandlers = new ArrayList<IPointHandler>();

  public PointConfiguration(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPoints(ICharacterId characterId) {
    int value = 0;
    for (IPointHandler handler : allHandlers) {
      value += handler.getPoints(characterId);
    }
    return String.valueOf(value);
  }

  public void addHandlers(Collection<IPointHandler> handlers) {
    allHandlers.addAll(handlers);
  }

  public void addHandler(IPointHandler handler) {
    allHandlers.add(handler);
  }
}