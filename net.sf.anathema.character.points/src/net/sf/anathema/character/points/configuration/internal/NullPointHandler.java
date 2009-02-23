package net.sf.anathema.character.points.configuration.internal;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.IPointHandler;

public class NullPointHandler extends UnconfiguredExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(ICharacterId characterId) {
    return 0;
  }
}