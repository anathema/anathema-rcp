package net.sf.anathema.character.core.model.internal;

import org.eclipse.core.resources.IFolder;

import net.sf.anathema.character.core.model.IPointHandler;

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
  public String getPoints(IFolder folder) {
    return String.valueOf(handler.getPoints(folder));
  }
}