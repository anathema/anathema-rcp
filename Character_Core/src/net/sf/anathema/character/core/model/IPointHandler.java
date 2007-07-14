package net.sf.anathema.character.core.model;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IPointHandler extends IExecutableExtension {

  public int getPoints(ICharacterId characterId);
}