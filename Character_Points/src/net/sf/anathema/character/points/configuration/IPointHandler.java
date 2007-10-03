package net.sf.anathema.character.points.configuration;

import net.sf.anathema.character.core.character.ICharacterId;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IPointHandler extends IExecutableExtension {

  public int getPoints(ICharacterId characterId);
}