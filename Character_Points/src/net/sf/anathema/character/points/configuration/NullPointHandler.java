package net.sf.anathema.character.points.configuration;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;

public class NullPointHandler extends AbstractExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(ICharacterId characterId) {
    return 0;
  }
}