package net.sf.anathema.character.attributes.bonuspoints;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IPointHandler;

public class AttributeBonusPointHandler extends AbstractExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(ICharacterId characterId) {
    return 0;
  }
}