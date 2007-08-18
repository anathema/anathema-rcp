package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.points.configuration.IPointHandler;

public class FreebiesBonusPointReducer extends AbstractExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(ICharacterId characterId) {
    return -100;
  }
}